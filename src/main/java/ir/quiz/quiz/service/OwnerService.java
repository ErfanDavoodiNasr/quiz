package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.response.JwtTokenResponse;

public interface OwnerService {
    JwtTokenResponse login(String username, String password);
}
