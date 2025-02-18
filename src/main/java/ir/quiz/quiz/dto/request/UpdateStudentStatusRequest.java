package ir.quiz.quiz.dto.request;


import ir.quiz.quiz.model.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStudentStatusRequest {
    @NotNull
    private Long id;
    @NotNull
    private Status status;
}
