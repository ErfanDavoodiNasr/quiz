package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.QuizRequest;
import ir.quiz.quiz.model.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    Boolean save(QuizRequest quizRequest);

    Optional<Quiz> findById(Long id);

    Optional<Quiz> findByCourseId(Long courseId);

    Optional<Quiz> findReferenceById(Long id);

    Optional<List<Quiz>> findAllByCourseId(Number courseId);

    Optional<List<Quiz>> findAll();
}
