package ir.quiz.quiz.model.dto;

import ir.quiz.quiz.model.Course;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PrincipalUpdateRequest {

    @NotNull
    private Long id;

    @Size(min = 2, max = 50)
    private String firstName;

    @Size(min = 2, max = 50)
    private String lastName;

    @Size(min = 10, max = 10)
    private String nationalCode;

    @Size(min = 11, max = 11)
    private String phoneNumber;

    @NotNull
    private List<Course> courses;
}
