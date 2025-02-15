package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
