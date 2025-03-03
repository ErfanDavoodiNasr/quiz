package ir.quiz.quiz.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuizQuestionRequest {

    @NotNull
    private Long questionId;

    @NotNull
    private Double score;
}
