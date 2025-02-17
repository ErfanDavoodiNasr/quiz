package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<List<Student>> findAllByStatusIsLike(Status status);

    Optional<Student> findByUsernameAndPassword(String username, String password);
}
