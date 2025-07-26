package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {

    Optional<Teacher> findByUsername(String username);
}
