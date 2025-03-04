package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.response.OwnerResponse;

import java.util.Optional;

public interface OwnerService {
    Optional<OwnerResponse> login(String username, String password);
}
