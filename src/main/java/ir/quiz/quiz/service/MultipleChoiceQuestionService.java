package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.MultipleChoiceQuestionRequest;
import ir.quiz.quiz.dto.request.MultipleChoiceQuestionUpdateRequest;
import ir.quiz.quiz.dto.request.QuestionOptionRequest;
import ir.quiz.quiz.dto.response.MultipleChoiceQuestionResponse;

import java.util.List;
import java.util.Optional;

public interface MultipleChoiceQuestionService {
    MultipleChoiceQuestionResponse save(MultipleChoiceQuestionRequest req);

    MultipleChoiceQuestionResponse update(MultipleChoiceQuestionUpdateRequest req);

    Boolean remove(Long id);

    Optional<List<MultipleChoiceQuestionResponse>> findAll();

    Boolean addOptionTOQuestion(QuestionOptionRequest optionRequest);

    Optional<MultipleChoiceQuestionResponse> findById(Long id);
}
