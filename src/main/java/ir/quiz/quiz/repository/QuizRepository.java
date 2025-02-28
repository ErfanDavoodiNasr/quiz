package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<List<Quiz>> findAllByCourse_IdAndTeacher_Id(Number courseId, Number teacherId);
}
