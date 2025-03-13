package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.quiz.StudentInQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentInQuizRepository extends JpaRepository<StudentInQuiz, Long> {
    Optional<StudentInQuiz> findByQuiz_IdAndStudent_Id(Number quizId, Number studentId);
}
