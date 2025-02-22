package ir.quiz.quiz.dto.request;


import ir.quiz.quiz.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequest {

    @NotBlank(message = "Username is required and cannot be empty.")
    private String username;

    @NotBlank(message = "Password is required and cannot be empty.")
    private String password;

    @NotNull(message = "User role is required.")
    private UserRole userRole;
}