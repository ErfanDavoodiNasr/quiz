package ir.quiz.quiz.repository;

import ir.quiz.quiz.dto.response.OwnerResponse;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository {
    Optional<OwnerResponse> findByUsername(String username);
}
