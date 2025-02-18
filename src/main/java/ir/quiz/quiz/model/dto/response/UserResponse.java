package ir.quiz.quiz.model.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class UserResponse {
    private String firstName;
    private String lastName;
    private String username;
}
