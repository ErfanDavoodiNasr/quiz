package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.dto.response.OwnerResponse;
import ir.quiz.quiz.exception.OwnerNotFoundException;
import ir.quiz.quiz.exception.StudentNotFoundException;
import ir.quiz.quiz.repository.OwnerRepository;
import ir.quiz.quiz.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Optional<OwnerResponse> findByUsernameAndPassword(String username, String password) {
        Optional<OwnerResponse> owner = ownerRepository.findByUsername(username);
        if (owner.isEmpty()) {
            throw new OwnerNotFoundException("owner not found");
        }
        if (bCryptPasswordEncoder.matches(password, owner.get().getPassword())) {
            return owner;
        } else {
            throw new StudentNotFoundException("your username or password is wrong");
        }
    }
}