package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.quiz.MultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion,Long> {
}
