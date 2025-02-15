package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
