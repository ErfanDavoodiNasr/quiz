package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.AnnotationQuestionRequest;
import ir.quiz.quiz.dto.request.MultipleChoiceQuestionRequest;
import ir.quiz.quiz.dto.request.QuizRequest;
import ir.quiz.quiz.dto.request.QuizUpdateRequest;
import ir.quiz.quiz.dto.response.MultipleQuizQuestionResponsePage;
import ir.quiz.quiz.dto.response.QuizQuestionResponsePage;
import ir.quiz.quiz.model.quiz.QuestionType;
import ir.quiz.quiz.model.quiz.Quiz;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    Boolean save(QuizRequest quizRequest);

    Quiz save(Quiz quiz);

    Optional<Quiz> findById(Long id);

    Optional<List<Quiz>> findAllByCourseIdAndTeacherId(Long courseId, Long teacherId);

    Optional<List<Quiz>> findAll();

    Boolean remove(Long id);

    Quiz update(QuizUpdateRequest quizUpdateRequest);

    Boolean addReadyQuestionToQuiz(Long questionId, Long quizId, Double score, QuestionType questionType);

    Boolean addNewMultipleQuestionToQuiz(MultipleChoiceQuestionRequest multipleChoiceQuestionRequest, Long quizId, Double score);

    Boolean addNewAnnotationQuestionToQuiz(AnnotationQuestionRequest annotationQuestionRequest, Long quizId, Double score);

    List<QuizQuestionResponsePage> seeAnnotationQuizQuestion(Long studentId, Long quizId, Pageable pageable);

    List<MultipleQuizQuestionResponsePage> seeMultipleChoiceQuizQuestion(Long studentId, Long quizId, Pageable pageable);

    Boolean answerAnnotationQuestion(Long studentId, Long quizQuestionId, Long quizId, String answer);

    Boolean answerMultipleQuestion(Long studentId, Long quizQuestionId, Long quizId, String answer);

    Boolean submitQuiz(Long quizId, Long StudentId);
}
