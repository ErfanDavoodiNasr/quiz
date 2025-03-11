package ir.quiz.quiz.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerRequest {
    @NotNull
    private Long studentId;
    @NotNull
    private Long questionId;
    @NotBlank
    private String answer;
}
