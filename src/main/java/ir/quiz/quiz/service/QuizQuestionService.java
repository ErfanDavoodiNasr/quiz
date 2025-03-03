package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.QuizQuestionRequest;

public interface QuizQuestionService {
    Boolean save(QuizQuestionRequest quizQuestion);
}
