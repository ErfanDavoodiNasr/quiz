package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.AnnotationQuestionRequest;
import ir.quiz.quiz.model.quiz.AnnotationQuestion;
import ir.quiz.quiz.model.quiz.MultipleChoiceQuestion;

import java.util.List;
import java.util.Optional;

public interface AnnotationQuestionService {
    AnnotationQuestion save(AnnotationQuestionRequest annotationQuestion);

    Optional<List<AnnotationQuestion>> findAll();

    Optional<AnnotationQuestion> findById(Long id);
}
