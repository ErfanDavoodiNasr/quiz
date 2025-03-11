package ir.quiz.quiz.repository;


import ir.quiz.quiz.model.quiz.QuizQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionAnswerRepository extends JpaRepository<QuizQuestionAnswer, Long> {
}
