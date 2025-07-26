package ir.quiz.quiz.dto.request;


import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class PersonRequest {

    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters.")
    private String firstName;

    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters.")
    private String lastName;

    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters.")
    private String username;

    @Size(min = 8, max = 50, message = "Password must be at least 8 characters long.")
    private String password;
}