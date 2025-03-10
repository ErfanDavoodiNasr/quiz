package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.dto.request.AnnotationQuestionRequest;
import ir.quiz.quiz.dto.request.AnnotationQuestionUpdateRequest;
import ir.quiz.quiz.dto.response.AnnotationQuestionResponse;
import ir.quiz.quiz.exception.CourseNotFoundException;
import ir.quiz.quiz.exception.QuestionNotFoundException;
import ir.quiz.quiz.exception.TeacherNotFoundException;
import ir.quiz.quiz.mapper.AnnotationQuestionResponseMapper;
import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.quiz.AnnotationQuestion;
import ir.quiz.quiz.repository.AnnotationQuestionRepository;
import ir.quiz.quiz.repository.CourseRepository;
import ir.quiz.quiz.repository.TeacherRepository;
import ir.quiz.quiz.service.AnnotationQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnnotationQuestionServiceImpl implements AnnotationQuestionService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final AnnotationQuestionRepository annotationQuestionRepository;
    private final AnnotationQuestionResponseMapper annotationQuestionResponseMapper;

    private static AnnotationQuestion convertDtoToEntity(AnnotationQuestionRequest annotationQuestion, Optional<Teacher> teacher, Optional<Course> course) {
        return AnnotationQuestion.builder()
                .teacher(teacher.get())
                .course(course.get())
                .title(annotationQuestion.getTitle())
                .questionText(annotationQuestion.getQuestionText())
                .build();
    }

    @Override
    public AnnotationQuestionResponse save(AnnotationQuestionRequest annotationQuestion) {
        Optional<Course> course = checkCourseIsExist(annotationQuestion);
        Optional<Teacher> teacher = checkTeacherIsExist(annotationQuestion);
        AnnotationQuestion result = convertDtoToEntity(annotationQuestion, teacher, course);
        return annotationQuestionResponseMapper.convertEntityToDto(annotationQuestionRepository.save(result));
    }

    @Override
    public AnnotationQuestionResponse update(AnnotationQuestionUpdateRequest req) {
        Optional<AnnotationQuestion> question = annotationQuestionRepository.findById(req.getId());
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("no question found");
        }
        Optional<Course> course = courseRepository.findById(req.getCourseId());
        if (course.isEmpty()) {
            throw new CourseNotFoundException("no course found");
        }
        Optional<Teacher> teacher = teacherRepository.findById(req.getTeacherId());
        if (teacher.isEmpty()) {
            throw new TeacherNotFoundException("no teacher found");
        }
        question.get().setQuestionText(req.getQuestionText());
        question.get().setTitle(req.getTitle());
        question.get().setCourse(course.get());
        question.get().setTeacher(teacher.get());
        AnnotationQuestion result = annotationQuestionRepository.save(question.get());
        return annotationQuestionResponseMapper.convertEntityToDto(result);
    }

    @Override
    public Boolean remove(Long id) {
        try {
            Optional<AnnotationQuestion> question = annotationQuestionRepository.findById(id);
            if (question.isEmpty()) {
                throw new QuestionNotFoundException("no question found");
            }
            question.get().setCourse(null);
            question.get().setTeacher(null);
            annotationQuestionRepository.delete(question.get());
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Optional<List<AnnotationQuestionResponse>> findAll() {
        List<AnnotationQuestion> result = annotationQuestionRepository.findAll();
        if (result.isEmpty()) {
            throw new QuestionNotFoundException("no annotation question found");
        }
        return Optional.ofNullable(annotationQuestionResponseMapper.convertEntityToDto(result));
    }

    @Override
    public Optional<AnnotationQuestionResponse> findById(Long id) {
        Optional<AnnotationQuestion> question = annotationQuestionRepository.findById(id);
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("no question found");
        }
        return Optional.of(annotationQuestionResponseMapper.convertEntityToDto(question.get()));
    }

    @Override
    public Optional<List<AnnotationQuestionResponse>> findAllByCourseIdAndTeacherId(Number courseId, Number teacherId) {
        Optional<List<AnnotationQuestionResponse>> result = annotationQuestionRepository.findAllByCourseIdAndTeacherId(courseId, teacherId);
        if (result.isEmpty()){
            throw new QuestionNotFoundException("no question found");
        }
        return result;
    }

    @Override
    public Optional<List<AnnotationQuestionResponse>> findAllByTeacherId(Number teacherId) {
        Optional<List<AnnotationQuestionResponse>> result = annotationQuestionRepository.findAllByTeacherId(teacherId);
        if (result.isEmpty()){
            throw new QuestionNotFoundException("no question found");
        }
        return result;
    }

    private Optional<Course> checkCourseIsExist(AnnotationQuestionRequest annotationQuestion) {
        Optional<Course> course = courseRepository.findById(annotationQuestion.getCourseId());
        if (course.isEmpty()) {
            throw new CourseNotFoundException("no course found");
        }
        return course;
    }

    private Optional<Teacher> checkTeacherIsExist(AnnotationQuestionRequest annotationQuestion) {
        Optional<Teacher> teacher = teacherRepository.findById(annotationQuestion.getTeacherId());
        if (teacher.isEmpty()) {
            throw new TeacherNotFoundException("no teacher found");
        }
        return teacher;
    }
}
