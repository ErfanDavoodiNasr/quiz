package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findByCourse_Id(Number courseId);

    Optional<List<Quiz>> findAllByCourse_Id(Number courseId);
}
