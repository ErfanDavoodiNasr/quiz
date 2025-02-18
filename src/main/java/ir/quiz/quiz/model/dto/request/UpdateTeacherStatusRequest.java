package ir.quiz.quiz.model.dto.request;


import ir.quiz.quiz.model.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTeacherStatusRequest {
    @NotNull
    private Long id;
    @NotNull
    private Status status;
}
