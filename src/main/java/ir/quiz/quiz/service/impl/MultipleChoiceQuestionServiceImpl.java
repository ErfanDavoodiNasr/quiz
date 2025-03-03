package ir.quiz.quiz.service.impl;


import ir.quiz.quiz.dto.request.MultipleChoiceQuestionRequest;
import ir.quiz.quiz.dto.request.QuestionOptionRequest;
import ir.quiz.quiz.exception.CourseNotFoundException;
import ir.quiz.quiz.exception.QuestionNotFoundException;
import ir.quiz.quiz.exception.TeacherNotFoundException;
import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.quiz.MultipleChoiceQuestion;
import ir.quiz.quiz.model.quiz.QuestionOption;
import ir.quiz.quiz.repository.CourseRepository;
import ir.quiz.quiz.repository.MultipleChoiceQuestionRepository;
import ir.quiz.quiz.repository.QuestionOptionRepository;
import ir.quiz.quiz.repository.TeacherRepository;
import ir.quiz.quiz.service.MultipleChoiceQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MultipleChoiceQuestionServiceImpl implements MultipleChoiceQuestionService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final QuestionOptionRepository questionOptionRepository;

    private static QuestionOption convertDtoToEntity(QuestionOptionRequest optionRequest) {
        QuestionOption questionOption = QuestionOption.builder()
                .text(optionRequest.getText())
                .isCorrect(optionRequest.getIsCorrect())
                .build();
        return questionOption;
    }

    private static MultipleChoiceQuestion convertDtoToEntity(MultipleChoiceQuestionRequest multipleChoiceQuestion, Optional<Teacher> teacher, Optional<Course> course) {
        MultipleChoiceQuestion result = MultipleChoiceQuestion.builder()
                .teacher(teacher.get())
                .course(course.get())
                .title(multipleChoiceQuestion.getTitle())
                .questionText(multipleChoiceQuestion.getQuestionText())
                .build();
        return result;
    }

    @Override
    public MultipleChoiceQuestion save(MultipleChoiceQuestionRequest multipleChoiceQuestion) {
        Optional<Course> course = checkCourseIsExist(multipleChoiceQuestion);
        Optional<Teacher> teacher = checkTeacherIsExist(multipleChoiceQuestion);
        MultipleChoiceQuestion result = convertDtoToEntity(multipleChoiceQuestion, teacher, course);
        return multipleChoiceQuestionRepository.save(result);
    }

    @Override
    public Optional<List<MultipleChoiceQuestion>> findAll() {
        return Optional.ofNullable(multipleChoiceQuestionRepository.findAll());
    }

    @Override
    public Boolean addOptionTOQuestion(QuestionOptionRequest optionRequest) {
        Optional<MultipleChoiceQuestion> question = checkQuestionIsExist(optionRequest);
        QuestionOption questionOption = convertDtoToEntity(optionRequest);
        QuestionOption option = questionOptionRepository.save(questionOption);
        question.get().getQuestionOptions().add(option);
        MultipleChoiceQuestion result = multipleChoiceQuestionRepository.save(question.get());
        return result.getQuestionOptions().contains(option);
    }

    @Override
    public Optional<MultipleChoiceQuestion> findById(Long id) {
        return multipleChoiceQuestionRepository.findById(id);
    }

    private Optional<MultipleChoiceQuestion> checkQuestionIsExist(QuestionOptionRequest optionRequest) {
        Optional<MultipleChoiceQuestion> question = multipleChoiceQuestionRepository.findById(optionRequest.getQuestionId());
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("no question found");
        }
        return question;
    }

    private Optional<Course> checkCourseIsExist(MultipleChoiceQuestionRequest multipleChoiceQuestion) {
        Optional<Course> course = courseRepository.findById(multipleChoiceQuestion.getCourseId());
        if (course.isEmpty()) {
            throw new CourseNotFoundException("no course found");
        }
        return course;
    }

    private Optional<Teacher> checkTeacherIsExist(MultipleChoiceQuestionRequest multipleChoiceQuestion) {
        Optional<Teacher> teacher = teacherRepository.findById(multipleChoiceQuestion.getTeacherId());
        if (teacher.isEmpty()) {
            throw new TeacherNotFoundException("no teacher found");
        }
        return teacher;
    }
}
