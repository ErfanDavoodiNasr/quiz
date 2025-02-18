package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> , JpaSpecificationExecutor<Teacher> {
    Optional<List<Teacher>> findAllByStatusIsLike(Status status);

    Optional<Teacher> findByUsername(String username);
}
