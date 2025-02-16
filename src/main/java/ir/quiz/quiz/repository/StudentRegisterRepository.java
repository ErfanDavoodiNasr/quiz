package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.StudentRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRegisterRepository extends JpaRepository<StudentRegister, Long> {
    Optional<List<StudentRegister>> findAllByPrincipal_Id(Long id);
}
