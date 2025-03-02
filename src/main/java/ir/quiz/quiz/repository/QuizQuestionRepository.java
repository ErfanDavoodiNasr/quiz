package ir.quiz.quiz.repository;


import ir.quiz.quiz.model.quiz.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion,Long> {
}
