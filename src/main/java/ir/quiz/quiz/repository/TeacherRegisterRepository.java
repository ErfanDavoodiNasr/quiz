package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.TeacherRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRegisterRepository extends JpaRepository<TeacherRegister, Long> {
    Optional<List<TeacherRegister>> findAllByPrincipal_Id(Long id);
}
