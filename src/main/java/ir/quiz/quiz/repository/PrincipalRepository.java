package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.Principal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrincipalRepository extends JpaRepository<Principal, Long> {
}
