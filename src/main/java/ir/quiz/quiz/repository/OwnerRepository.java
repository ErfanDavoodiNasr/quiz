package ir.quiz.quiz.repository;

import ir.quiz.quiz.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByUsernameAndPassword(String username, String password);
}
