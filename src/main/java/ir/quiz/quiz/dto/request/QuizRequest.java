package ir.quiz.quiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizRequest {
    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String startAt;

    @NotBlank
    private String endAt;

    @NotNull
    private Long courseId;

    @NotNull
    private Long teacherId;
}
