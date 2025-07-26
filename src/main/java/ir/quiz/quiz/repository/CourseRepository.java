package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<List<Course>> findAllByTeacher_Id(Number teacherId);
}
