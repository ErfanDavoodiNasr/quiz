package ir.quiz.quiz.service;

import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.dto.CourseRequest;

public interface CourseService {
    Boolean save(CourseRequest courseRequest);

    Course update(Course course);
}
