package ir.quiz.quiz.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
}
