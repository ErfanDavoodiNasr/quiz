package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
