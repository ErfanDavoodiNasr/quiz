package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.quiz.AnnotationQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnnotationQuestionRepository extends JpaRepository<AnnotationQuestion, Long> {
    Optional<List<AnnotationQuestion>> findAllByCourse_IdAndTeacher_Id(Long courseId, Long teacherId);

    Optional<List<AnnotationQuestion>> findAllByTeacher_Id(Long teacherId);
}
