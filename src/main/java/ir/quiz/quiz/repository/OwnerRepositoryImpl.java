package ir.quiz.quiz.repository;

import io.github.cdimascio.dotenv.Dotenv;
import ir.quiz.quiz.dto.response.OwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OwnerRepositoryImpl implements OwnerRepository {

    private final Dotenv dotenv;

    @Override
    public Optional<OwnerResponse> findByUsername(String username) {
        OwnerResponse owner = OwnerResponse.builder().username(dotenv.get("OWNER_USERNAME")).password(dotenv.get("OWNER_PASSWORD")).build();
        if (!owner.getUsername().equals(username)) {
            Optional.empty();
        }
        return Optional.ofNullable(owner);
    }
}
