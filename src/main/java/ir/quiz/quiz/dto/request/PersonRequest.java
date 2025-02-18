package ir.quiz.quiz.dto.request;


import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class PersonRequest {

    @Size(min = 2, max = 50)
    private String firstName;

    @Size(min = 2, max = 50)
    private String lastName;

    @Size(min = 3, max = 50)
    private String username;

    @Size(min = 8, max = 50)
    private String password;
}
