package ir.quiz.quiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class AnnotationQuestionRequest {
    @NotNull
    private String title;

    @NotBlank
    private String questionText;

    @NotNull
    private Long teacherId;

    @NotNull
    private Long courseId;
}
