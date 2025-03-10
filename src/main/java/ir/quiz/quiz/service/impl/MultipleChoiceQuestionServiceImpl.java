package ir.quiz.quiz.service.impl;


import ir.quiz.quiz.dto.request.MultipleChoiceQuestionRequest;
import ir.quiz.quiz.dto.request.MultipleChoiceQuestionUpdateRequest;
import ir.quiz.quiz.dto.request.QuestionOptionRequest;
import ir.quiz.quiz.dto.response.MultipleChoiceQuestionResponse;
import ir.quiz.quiz.exception.CourseNotFoundException;
import ir.quiz.quiz.exception.QuestionNotFoundException;
import ir.quiz.quiz.exception.TeacherNotFoundException;
import ir.quiz.quiz.mapper.MultipleChoiceQuestionResponseMapper;
import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.quiz.MultipleChoiceQuestion;
import ir.quiz.quiz.model.quiz.QuestionOption;
import ir.quiz.quiz.repository.CourseRepository;
import ir.quiz.quiz.repository.MultipleChoiceQuestionRepository;
import ir.quiz.quiz.repository.QuestionOptionRepository;
import ir.quiz.quiz.repository.TeacherRepository;
import ir.quiz.quiz.service.MultipleChoiceQuestionService;
import ir.quiz.quiz.service.QuestionOptionService;
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
    private final QuestionOptionService questionOptionService;
    private final QuestionOptionRepository questionOptionRepository;
    private final MultipleChoiceQuestionResponseMapper multipleChoiceQuestionResponseMapper;

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
    public MultipleChoiceQuestionResponse save(MultipleChoiceQuestionRequest multipleChoiceQuestion) {
        Optional<Course> course = checkCourseIsExist(multipleChoiceQuestion);
        Optional<Teacher> teacher = checkTeacherIsExist(multipleChoiceQuestion);
        MultipleChoiceQuestion result = convertDtoToEntity(multipleChoiceQuestion, teacher, course);
        MultipleChoiceQuestion question = multipleChoiceQuestionRepository.saveAndFlush(result);
        if (question.getId() != null) {
            List<QuestionOptionRequest> questionOptions = multipleChoiceQuestion.getOptions();
            for (QuestionOptionRequest questionOption : questionOptions) {
                questionOption.setQuestionId(question.getId());
                Boolean a = addOptionTOQuestion(questionOption);
            }
        }
        return findById(question.getId()).get();
    }

    @Override
    public MultipleChoiceQuestionResponse update(MultipleChoiceQuestionUpdateRequest req) {
        Optional<MultipleChoiceQuestion> question = multipleChoiceQuestionRepository.findById(req.getId());
        Optional<Course> course = courseRepository.findById(req.getCourseId());
        if (course.isEmpty()) {
            throw new CourseNotFoundException("no course found");
        }
        Optional<Teacher> teacher = teacherRepository.findById(req.getTeacherId());
        if (teacher.isEmpty()) {
            throw new TeacherNotFoundException("no teacher found");
        }
        question.get().setQuestionText(req.getQuestionText());
        question.get().setTeacher(teacher.get());
        question.get().setCourse(course.get());
        question.get().setTitle(req.getTitle());
        MultipleChoiceQuestion result = multipleChoiceQuestionRepository.save(question.get());
        return multipleChoiceQuestionResponseMapper.convertEntityToDto(result);
    }

    @Override
    public Boolean remove(Long id) {
        try {
            Optional<MultipleChoiceQuestion> question = multipleChoiceQuestionRepository.findById(id);
            if (question.isEmpty()) {
                throw new QuestionNotFoundException("no question found");
            }
            question.get().setCourse(null);
            question.get().setTeacher(null);
            multipleChoiceQuestionRepository.delete(question.get());
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Optional<List<MultipleChoiceQuestionResponse>> findAll() {
        List<MultipleChoiceQuestion> result = multipleChoiceQuestionRepository.findAll();
        if (result.isEmpty()) {
            throw new QuestionNotFoundException("no question found");
        }
        return Optional.ofNullable(multipleChoiceQuestionResponseMapper.convertEntityToDto(result));
    }

    @Override
    public Boolean addOptionTOQuestion(QuestionOptionRequest optionRequest) {
        Optional<MultipleChoiceQuestion> question = checkQuestionIsExist(optionRequest);
        QuestionOption questionOption = convertDtoToEntity(optionRequest);
        questionOption.setMultipleChoiceQuestion(question.get());
        QuestionOption option = questionOptionRepository.saveAndFlush(questionOption);
        return option.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Optional<MultipleChoiceQuestionResponse> findById(Long id) {
        Optional<MultipleChoiceQuestion> question = multipleChoiceQuestionRepository.findById(id);
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("no question found");
        }
        return Optional.ofNullable(multipleChoiceQuestionResponseMapper.convertEntityToDto(question.get()));
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
