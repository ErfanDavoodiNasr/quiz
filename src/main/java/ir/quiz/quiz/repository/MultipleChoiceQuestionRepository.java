package ir.quiz.quiz.repository;

import ir.quiz.quiz.dto.response.AnnotationQuestionResponse;
import ir.quiz.quiz.dto.response.MultipleChoiceQuestionResponse;
import ir.quiz.quiz.dto.response.QuestionResponse;
import ir.quiz.quiz.model.quiz.MultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, Long> {
    Optional<List<MultipleChoiceQuestionResponse>> findAllByCourseIdAndTeacherId(Number courseId, Number teacherId);
    Optional<List<MultipleChoiceQuestionResponse>> findAllByTeacherId(Number teacherId);
}
