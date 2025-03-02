package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.quiz.AnnotationQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnotationQuestionRepository extends JpaRepository<AnnotationQuestion,Long> {
}
