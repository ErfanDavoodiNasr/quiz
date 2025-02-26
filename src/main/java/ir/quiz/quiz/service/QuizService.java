package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.QuizRequest;
import ir.quiz.quiz.dto.request.QuizUpdateRequest;
import ir.quiz.quiz.model.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    Boolean save(QuizRequest quizRequest);

    Optional<Quiz> findById(Long id);

    Optional<List<Quiz>> findAllByCourseIdAndTeacherId(Long courseId, Long teacherId);

    Optional<List<Quiz>> findAll();

    Boolean remove(Long id);

    Quiz update(QuizUpdateRequest quizUpdateRequest);
}
