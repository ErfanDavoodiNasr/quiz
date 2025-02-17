package ir.quiz.quiz.service;

import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.dto.CourseRequest;

import java.util.Optional;

public interface CourseService {
    Boolean save(CourseRequest courseRequest);

    Course update(Course course);

    Optional<Course> findById(Long id);
}
