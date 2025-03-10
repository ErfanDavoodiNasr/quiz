package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.QuestionOptionRequest;
import ir.quiz.quiz.dto.request.QuestionOptionUpdateRequest;
import ir.quiz.quiz.dto.response.QuestionOptionResponse;

import java.util.List;
import java.util.Optional;

public interface QuestionOptionService {
    Boolean save(QuestionOptionRequest questionOption);

    QuestionOptionResponse update(QuestionOptionUpdateRequest questionOptionUpdateRequest);

    Boolean remove(Long id);

    Optional<List<QuestionOptionResponse>> findAll();

    Optional<QuestionOptionResponse> findById(Long id);
}
