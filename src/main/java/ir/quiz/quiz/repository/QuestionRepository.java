package ir.quiz.quiz.repository;

import ir.quiz.quiz.dto.response.QuestionResponse;
import ir.quiz.quiz.model.quiz.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
