package ir.quiz.quiz.dto.request;


import ir.quiz.quiz.model.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentUpdateStatusRequest {

    @NotNull(message = "Student ID is required.")
    private Long id;

    @NotNull(message = "Status is required.")
    private Status status;
}