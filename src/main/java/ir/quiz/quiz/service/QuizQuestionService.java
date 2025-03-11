package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.QuizQuestionRequest;
import ir.quiz.quiz.dto.response.QuizQuestionResponse;
import ir.quiz.quiz.dto.response.QuizQuestionUpdateRequest;

public interface QuizQuestionService {
    Boolean save(QuizQuestionRequest quizQuestion);

    QuizQuestionResponse update(QuizQuestionUpdateRequest quizQuestionUpdateRequest);

    Boolean remove(Long id);
}
