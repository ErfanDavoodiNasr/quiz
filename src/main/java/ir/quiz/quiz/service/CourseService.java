package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.CourseRequest;
import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Quiz;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Boolean save(CourseRequest courseRequest);

    Course update(Course course);

    Optional<Course> findById(Long id);

    Optional<List<Quiz>> findAllQuizzesByCourseId(Long id);

    Optional<List<Course>> findAll();

    Boolean removeStudentFromCourse(Long studentId, Long courseId);

    Boolean addStudentToCourse(Long studentId, Long courseId);

    Boolean removeTeacherFromCourse(Long courseId);

    Boolean addTeacherToCourse(Long teacherId, Long courseId);
}
