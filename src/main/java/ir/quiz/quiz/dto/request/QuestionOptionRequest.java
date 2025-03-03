package ir.quiz.quiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuestionOptionRequest {
    @NotNull
    private Long questionId;

    @NotBlank
    private String text;

    @NotNull
    private Boolean isCorrect;
}
