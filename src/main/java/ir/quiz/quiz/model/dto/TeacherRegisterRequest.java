package ir.quiz.quiz.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class TeacherRegisterRequest {

    @NotNull
    private Long teacherId;

    @NotNull
    private Long principalId;
}
