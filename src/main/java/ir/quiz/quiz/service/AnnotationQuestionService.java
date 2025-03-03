package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.AnnotationQuestionRequest;
import ir.quiz.quiz.model.quiz.AnnotationQuestion;

import java.util.List;
import java.util.Optional;

public interface AnnotationQuestionService {
    Long save(AnnotationQuestionRequest annotationQuestion);

    Optional<List<AnnotationQuestion>> findAll();
}
