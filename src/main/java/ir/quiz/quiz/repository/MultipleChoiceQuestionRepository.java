package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.quiz.MultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, Long> {
    Optional<List<MultipleChoiceQuestion>> findAllByCourse_IdAndTeacher_Id(Long courseId, Long teacherId);

    Optional<List<MultipleChoiceQuestion>> findAllByTeacher_Id(Long teacherId);
}
