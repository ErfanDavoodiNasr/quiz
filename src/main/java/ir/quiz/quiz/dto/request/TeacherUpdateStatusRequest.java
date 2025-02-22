package ir.quiz.quiz.dto.request;


import ir.quiz.quiz.model.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeacherUpdateStatusRequest {

    @NotNull(message = "Teacher ID is required.")
    private Long id;

    @NotNull(message = "Status is required.")
    private Status status;
}