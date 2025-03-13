package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.AnnotationQuestionRequest;
import ir.quiz.quiz.dto.request.AnnotationQuestionUpdateRequest;
import ir.quiz.quiz.dto.response.AnnotationQuestionResponse;

import java.util.List;
import java.util.Optional;

public interface AnnotationQuestionService {
    AnnotationQuestionResponse save(AnnotationQuestionRequest annotationQuestion);

    AnnotationQuestionResponse update(AnnotationQuestionUpdateRequest annotationQuestionUpdateRequest);

    Boolean remove(Long id);

    Optional<List<AnnotationQuestionResponse>> findAll();

    Optional<AnnotationQuestionResponse> findById(Long id);

    Optional<List<AnnotationQuestionResponse>> findAllByCourseIdAndTeacherId(Long courseId, Long teacherId);
}
