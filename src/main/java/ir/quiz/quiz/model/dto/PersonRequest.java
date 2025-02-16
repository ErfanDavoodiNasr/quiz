package ir.quiz.quiz.model.dto;


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

    @Size(min = 10, max = 10)
    private String nationalCode;

    @Size(min = 11, max = 11)
    private String phoneNumber;
}
