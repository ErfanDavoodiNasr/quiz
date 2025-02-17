package ir.quiz.quiz.model.dto;


import ir.quiz.quiz.model.UserRole;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequest {
    private String username;
    private String password;
    private UserRole userRole;
}
