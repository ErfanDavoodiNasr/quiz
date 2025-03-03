package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.dto.request.AnnotationQuestionRequest;
import ir.quiz.quiz.exception.CourseNotFoundException;
import ir.quiz.quiz.exception.TeacherNotFoundException;
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

    private static AnnotationQuestion convertDtoToEntity(AnnotationQuestionRequest annotationQuestion, Optional<Teacher> teacher, Optional<Course> course) {
        return AnnotationQuestion.builder()
                .teacher(teacher.get())
                .course(course.get())
                .title(annotationQuestion.getTitle())
                .questionText(annotationQuestion.getQuestionText())
                .build();
    }

    @Override
    public AnnotationQuestion save(AnnotationQuestionRequest annotationQuestion) {
        Optional<Course> course = checkCourseIsExist(annotationQuestion);
        Optional<Teacher> teacher = checkTeacherIsExist(annotationQuestion);
        AnnotationQuestion result = convertDtoToEntity(annotationQuestion, teacher, course);
        return annotationQuestionRepository.save(result);
    }

    @Override
    public Optional<List<AnnotationQuestion>> findAll() {
        return Optional.ofNullable(annotationQuestionRepository.findAll());
    }

    @Override
    public Optional<AnnotationQuestion> findById(Long id) {
        return annotationQuestionRepository.findById(id);
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
