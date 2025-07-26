package ir.quiz.quiz.dto.request;


import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentUpdateRequest {

    @NotNull(message = "Student ID is required.")
    private Long id;

    @NotBlank(message = "First name is required and cannot be empty.")
    private String firstName;

    @NotBlank(message = "Last name is required and cannot be empty.")
    private String lastName;

    @NotBlank(message = "Username is required and cannot be empty.")
    private String username;

    @NotBlank(message = "Password is required and cannot be empty.")
    private String password;

    private List<Course> courses;

    @NotNull(message = "Status is required.")
    private Status status;
}
