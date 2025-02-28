package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.dto.request.QuizRequest;
import ir.quiz.quiz.dto.request.QuizUpdateRequest;
import ir.quiz.quiz.exception.QuizNotFoundException;
import ir.quiz.quiz.mapper.QuizRequestMapper;
import ir.quiz.quiz.model.quiz.Quiz;
import ir.quiz.quiz.repository.CourseRepository;
import ir.quiz.quiz.repository.QuizRepository;
import ir.quiz.quiz.repository.TeacherRepository;
import ir.quiz.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final QuizRequestMapper quizRequestMapper;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public Boolean save(QuizRequest quizRequest) {
        Quiz quiz = quizRequestMapper.convertDtoToEntity(quizRequest);
        quiz.setCourse(courseRepository.findById(quizRequest.getCourseId()).get());
        quiz.setTeacher(teacherRepository.findById(quizRequest.getTeacherId()).get());
        // todo
        checkTimeIsValid(quiz.getStartAt(), quiz.getEndAt());
        Quiz result = quizRepository.save(quiz);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
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
        Optional<Quiz> quiz = quizRepository.findById(quizUpdateRequest.getId());
        if (quiz.isEmpty()) {
            throw new QuizNotFoundException("no quiz found");
        }
        Quiz finalQuiz = convertDtoToEntity(quizUpdateRequest, quiz);
        return quizRepository.save(finalQuiz);
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
}
