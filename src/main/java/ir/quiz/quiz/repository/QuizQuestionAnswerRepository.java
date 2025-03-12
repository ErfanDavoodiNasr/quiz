package ir.quiz.quiz.repository;


import ir.quiz.quiz.model.quiz.QuizQuestionAnswer;
import ir.quiz.quiz.model.quiz.StudentInQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NavigableMap;
import java.util.Optional;

@Repository
public interface QuizQuestionAnswerRepository extends JpaRepository<QuizQuestionAnswer, Long> {
    Optional<QuizQuestionAnswer> findByQuizQuestion_IdAndStudent_Id(Number quizQuestionId, Number studentId);

    Optional<List<QuizQuestionAnswer>> findAllByQuiz_Id(Number quizId);
}
