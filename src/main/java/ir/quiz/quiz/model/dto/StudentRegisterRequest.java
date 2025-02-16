package ir.quiz.quiz.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRegisterRequest {

    @NotNull
    private Long studentId;

    @NotNull
    private Long principalId;
}
