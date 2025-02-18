package ir.quiz.quiz.dto.request;


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
