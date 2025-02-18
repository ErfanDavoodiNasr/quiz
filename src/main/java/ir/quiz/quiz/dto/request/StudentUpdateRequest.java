package ir.quiz.quiz.dto.request;


import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentUpdateRequest {
    @NotNull
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    @NotEmpty
    private List<Course> courses;

    @NotNull
    private Status status;
}
