package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.RegisterToCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterToCourse, Long> {
}
