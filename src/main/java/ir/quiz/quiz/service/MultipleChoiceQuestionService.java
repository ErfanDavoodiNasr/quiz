package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.MultipleChoiceQuestionRequest;
import ir.quiz.quiz.dto.request.QuestionOptionRequest;
import ir.quiz.quiz.model.quiz.MultipleChoiceQuestion;

import java.util.List;
import java.util.Optional;

public interface MultipleChoiceQuestionService {
    Long save(MultipleChoiceQuestionRequest multipleChoiceQuestion);

    Optional<List<MultipleChoiceQuestion>> findAll();

    Boolean addOptionTOQuestion(QuestionOptionRequest optionRequest);
}
