package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.quiz.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionOptionRepository extends JpaRepository<QuestionOption,Long> {
}
