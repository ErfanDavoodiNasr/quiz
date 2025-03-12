package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.dto.request.AnnotationQuestionRequest;
import ir.quiz.quiz.dto.request.MultipleChoiceQuestionRequest;
import ir.quiz.quiz.dto.request.QuizRequest;
import ir.quiz.quiz.dto.request.QuizUpdateRequest;
import ir.quiz.quiz.dto.response.MultipleQuizQuestionResponsePage;
import ir.quiz.quiz.dto.response.QuizQuestionResponsePage;
import ir.quiz.quiz.exception.*;
import ir.quiz.quiz.mapper.MultipleQuizQuestionResponsePageMapper;
import ir.quiz.quiz.mapper.QuestionResponseMapper;
import ir.quiz.quiz.mapper.QuizQuestionResponsePageMapper;
import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.quiz.*;
import ir.quiz.quiz.repository.*;
import ir.quiz.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static ir.quiz.quiz.util.Help.checkTimeIsValid;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final AnnotationQuestionRepository annotationQuestionRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizQuestionAnswerRepository quizQuestionAnswerRepository;
    private final StudentRepository studentRepository;
    private final QuizQuestionResponsePageMapper quizQuestionResponsePageMapper;
    private final MultipleQuizQuestionResponsePageMapper multipleQuizQuestionResponsePageMapper;
    private final QuestionResponseMapper questionResponseMapper;

    @Override
    public Boolean save(QuizRequest quizRequest) {
        Quiz quiz = convertDtoToEntity(quizRequest);
        Optional<Course> course = checkCourseIsExist(quizRequest);
        Optional<Teacher> teacher = checkTeacherIsExist(quizRequest);
        quiz.setCourse(course.get());
        quiz.setTeacher(teacher.get());
        checkTimeIsValid(quiz.getStartAt(), quiz.getEndAt());
        Quiz result = quizRepository.save(quiz);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    private Optional<Teacher> checkTeacherIsExist(QuizRequest quizRequest) {
        Optional<Teacher> teacher = teacherRepository.findById(quizRequest.getTeacherId());
        if (teacher.isEmpty()) {
            throw new TeacherNotFoundException("no teacher found");
        }
        return teacher;
    }

    private Optional<Course> checkCourseIsExist(QuizRequest quizRequest) {
        Optional<Course> course = courseRepository.findById(quizRequest.getCourseId());
        if (course.isEmpty()) {
            throw new CourseNotFoundException("no course found");
        }
        return course;
    }

    @Override
    public Optional<Quiz> findById(Long id) {
        if (id != null) {
            return quizRepository.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Quiz>> findAllByCourseIdAndTeacherId(Long courseId, Long teacherId) {
        Optional<List<Quiz>> quizzes = checkQuizIsExist(courseId, teacherId);
        return quizzes;
    }

    private Optional<List<Quiz>> checkQuizIsExist(Long courseId, Long teacherId) {
        Optional<List<Quiz>> quizzes = quizRepository.findAllByCourse_IdAndTeacher_Id(courseId, teacherId);
        if (quizzes.isEmpty()) {
            throw new QuizNotFoundException("no quiz found");
        }
        return quizzes;
    }


    @Override
    public Optional<List<Quiz>> findAll() {
        return Optional.ofNullable(quizRepository.findAll());
    }

    @Override
    public Boolean remove(Long id) {
        Quiz quiz = quizRepository.getReferenceById(id);
        quizRepository.delete(quiz);
        Optional<Quiz> quiz2 = quizRepository.findById(id);
        return quiz2.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Quiz update(QuizUpdateRequest quizUpdateRequest) {
        Optional<Quiz> quiz = checkQuizIsExist(quizUpdateRequest);
        Quiz finalQuiz = convertDtoToEntity(quizUpdateRequest, quiz);
        return quizRepository.save(finalQuiz);
    }

    @Override
    public Boolean addReadyQuestionToQuiz(Long questionId, Long quizId, Double score, QuestionType questionType) {
        Optional<Quiz> quiz = checkQuizIsExist(quizRepository.findById(quizId));
        if (questionType == QuestionType.MULTIPLE) {
            return addMultipleQuestion(questionId, score, quiz);
        } else if (questionType == QuestionType.ANNOTATION) {
            return addAnnotationQuiz(questionId, score, quiz);
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean addNewMultipleQuestionToQuiz(MultipleChoiceQuestionRequest req, Long quizId, Double score) {
        MultipleChoiceQuestion question = convertDtoToEntity(req);
        question = multipleChoiceQuestionRepository.saveAndFlush(question);
        QuizQuestion quizQuestion = QuizQuestion.builder()
                .score(score)
                .question(question)
                .build();
        quizQuestion = quizQuestionRepository.saveAndFlush(quizQuestion);
        Optional<Quiz> quiz = getQuiz(quizRepository.findById(quizId));
        boolean added = quiz.get().getQuizQuestions().add(quizQuestion);
        quizRepository.save(quiz.get());
        return added && quiz.get().getQuizQuestions().contains(quizQuestion);
    }

    private MultipleChoiceQuestion convertDtoToEntity(MultipleChoiceQuestionRequest req) {
        return MultipleChoiceQuestion.builder()
                .questionText(req.getQuestionText())
                .title(req.getTitle())
                .course(courseRepository.findById(req.getCourseId()).get())
                .teacher(teacherRepository.findById(req.getTeacherId()).get())
                .build();
    }

    @Override
    public Boolean addNewAnnotationQuestionToQuiz(AnnotationQuestionRequest req, Long quizId, Double score) {
        AnnotationQuestion question = convertDtoToEntity(req);
        question = annotationQuestionRepository.saveAndFlush(question);
        QuizQuestion quizQuestion = QuizQuestion.builder()
                .score(score)
                .question(question)
                .build();
        quizQuestion = quizQuestionRepository.saveAndFlush(quizQuestion);
        Optional<Quiz> quiz = getQuiz(quizRepository.findById(quizId));
        boolean add = quiz.get().getQuizQuestions().add(quizQuestion);
        return quizRepository.save(quiz.get()).getQuizQuestions().contains(quizQuestion);
    }

    @Override
    public List<?> seeQuizQuestion(Long studentId, Long quizId, Integer index) {
        Optional<Quiz> quiz = getQuiz(quizRepository.findById(quizId));
        Optional<Student> student = getStudent(studentId);
        if (!quiz.get().getStudents().contains(student.get())) {
            quiz.get().getStudents().add(StudentInQuiz.builder().student(student.get()).isSubmit(Boolean.FALSE).build());
            quizRepository.saveAndFlush(quiz.get());
        }
        Optional<Quiz> result = quizRepository.findById(quizId);
        Question question = result.get().getQuizQuestions().get(index).getQuestion();
        if (question instanceof AnnotationQuestion) {
            QuizQuestionResponsePage quizQuestionResponsePages = quizQuestionResponsePageMapper.convertEntityToDto(result.get().getQuizQuestions().get(index));
            quizQuestionResponsePages.setDuration(Duration.between(LocalDateTime.now(), quiz.get().getEndAt()).getSeconds());
            quizQuestionResponsePages.setTotalIndex(quiz.get().getQuizQuestions().size());
            return List.of(quizQuestionResponsePages);
        } else if (question instanceof MultipleChoiceQuestion) {
            MultipleQuizQuestionResponsePage quizQuestionResponsePages = multipleQuizQuestionResponsePageMapper.convertEntityToDto((MultipleChoiceQuestion) result.get().getQuizQuestions().get(index).getQuestion());
            quizQuestionResponsePages.setQuestion(questionResponseMapper.convertEntityToDto(result.get().getQuizQuestions().get(index).getQuestion()));
            quizQuestionResponsePages.setScore(result.get().getQuizQuestions().get(index).getScore());
            quizQuestionResponsePages.setDuration(Duration.between(LocalDateTime.now(), quiz.get().getEndAt()).getSeconds());
            quizQuestionResponsePages.setTotalIndex(quiz.get().getQuizQuestions().size());
            return List.of(quizQuestionResponsePages);
        } else {
            throw new RuntimeException("there is some problem");
        }
    }

    public Boolean answerAnnotationQuestion(Long studentId, Long quizQuestionId, Long quizId, String answer) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (quiz.isEmpty()) {
            throw new QuizNotFoundException("no quiz found");
        }
        if (quiz.get().getEndAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("quiz time is over");
        }
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new StudentNotFoundException("no student found");
        }
        for (StudentInQuiz s : quiz.get().getStudents()) {
            if (s.getStudent().equals(student.get())) {
                if (s.getIsSubmit()) {
                    throw new RuntimeException("you submit this quiz before");
                }
            }
        }
        Optional<QuizQuestion> quizQuestion = quizQuestionRepository.findById(quizQuestionId);
        if (quizQuestion.isEmpty()) {
            throw new QuizNotFoundException("no question found");
        }
        QuizQuestionAnswer questionAnswer = QuizQuestionAnswer.builder()
                .student(student.get())
                .quizQuestion(quizQuestion.get())
                .userAnswer(answer)
                .build();
        QuizQuestionAnswer result = quizQuestionAnswerRepository.save(questionAnswer);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean answerMultipleQuestion(Long studentId, Long quizQuestionId, Long quizId, String answer) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new StudentNotFoundException("no student found");
        }
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (quiz.isEmpty()) {
            throw new QuizNotFoundException("no quiz found");
        }
        for (StudentInQuiz s : quiz.get().getStudents()) {
            if (s.getStudent().equals(student.get())) {
                if (s.getIsSubmit()) {
                    throw new RuntimeException("you submit this quiz before");
                }
            }
        }
        Optional<QuizQuestion> quizQuestion = quizQuestionRepository.findById(quizQuestionId);
        if (quizQuestion.isEmpty()) {
            throw new QuizNotFoundException("no question found");
        }
        MultipleChoiceQuestion question = (MultipleChoiceQuestion) quizQuestion.get().getQuestion();
        QuizQuestionAnswer questionAnswer = null;
        for (QuestionOption option : question.getOptions()) {
            if (option.getText().equalsIgnoreCase(answer)) {
                questionAnswer = QuizQuestionAnswer.builder()
                        .student(student.get())
                        .quizQuestion(quizQuestion.get())
                        .userAnswer(answer)
                        .score(quizQuestion.get().getScore())
                        .build();
            }
        }
        if (questionAnswer == null) {
            throw new RuntimeException("you entered invalid option");
        }
        QuizQuestionAnswer result = quizQuestionAnswerRepository.save(questionAnswer);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean submitQuiz(Long quizId, Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new StudentNotFoundException("no student found");
        }
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (quiz.isEmpty()) {
            throw new QuizNotFoundException("no quiz found");
        }
        for (StudentInQuiz s : quiz.get().getStudents()) {
            if (s.getStudent().equals(student.get())) {
                throw new RuntimeException("you submit this quiz before");
            }
        }
        quiz.get().getStudents().add(StudentInQuiz.builder().isSubmit(Boolean.TRUE).student(student.get()).build());
        Quiz result = quizRepository.save(quiz.get());
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    private Optional<Quiz> getQuiz(Optional<Quiz> quizRepository) {
        Optional<Quiz> quiz = quizRepository;
        if (quiz.isEmpty()) {
            throw new QuizNotFoundException("no quiz found");
        }
        if (quiz.get().getStartAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("you can't enter to quiz right now");
        }
        return quiz;
    }

    private Optional<Student> getStudent(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new StudentNotFoundException("no student found");
        }
        return student;
    }

    private AnnotationQuestion convertDtoToEntity(AnnotationQuestionRequest req) {
        return AnnotationQuestion.builder()
                .questionText(req.getQuestionText())
                .title(req.getTitle())
                .course(courseRepository.findById(req.getCourseId()).get())
                .teacher(teacherRepository.findById(req.getTeacherId()).get())
                .build();
    }

    private Optional<Quiz> checkQuizIsExist(Optional<Quiz> quizService) {
        Optional<Quiz> quiz = getQuiz(quizService);
        return quiz;
    }

    private boolean addMultipleQuestion(Long questionId, Double score, Optional<Quiz> quiz) {
        Optional<MultipleChoiceQuestion> question = multipleChoiceQuestionRepository.findById(questionId);
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("no quiz found");
        }
        QuizQuestion quizQuestion = QuizQuestion.builder()
                .question(question.get())
                .score(score)
                .build();
        quizQuestion = quizQuestionRepository.save(quizQuestion);
        boolean add = quiz.get().getQuizQuestions().add(quizQuestion);
        Quiz result = quizRepository.saveAndFlush(quiz.get());
        return result.getQuizQuestions().contains(quizQuestion);
    }

    private boolean addAnnotationQuiz(Long questionId, Double score, Optional<Quiz> quiz) {
        Optional<AnnotationQuestion> question = annotationQuestionRepository.findById(questionId);
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("no quiz found");
        }
        QuizQuestion quizQuestion = QuizQuestion.builder()
                .question(question.get())
                .score(score)
                .build();
        quizQuestion = quizQuestionRepository.save(quizQuestion);
        boolean add = quiz.get().getQuizQuestions().add(quizQuestion);
        Quiz result = quizRepository.saveAndFlush(quiz.get());
        return result.getQuizQuestions().contains(quizQuestion);
    }

    private Optional<Quiz> checkQuizIsExist(QuizUpdateRequest quizUpdateRequest) {
        Optional<Quiz> quiz = checkQuizIsExist(quizRepository.findById(quizUpdateRequest.getId()));
        return quiz;
    }

    private Quiz convertDtoToEntity(QuizUpdateRequest quizUpdateRequest, Optional<Quiz> quiz) {
        return Quiz.builder()
                .id(quiz.get().getId())
                .title(quizUpdateRequest.getTitle())
                .description(quizUpdateRequest.getDescription())
                .endAt(LocalDateTime.parse(quizUpdateRequest.getEndAt(), formatter))
                .startAt(LocalDateTime.parse(quizUpdateRequest.getStartAt(), formatter))
                .course(quiz.get().getCourse())
                .teacher(quiz.get().getTeacher())
                .build();
    }

    private Quiz convertDtoToEntity(QuizRequest quizRequest) {
        return Quiz.builder()
                .title(quizRequest.getTitle())
                .description(quizRequest.getDescription())
                .endAt(LocalDateTime.parse(quizRequest.getEndAt(), formatter))
                .startAt(LocalDateTime.parse(quizRequest.getStartAt(), formatter))
                .build();
    }
}
