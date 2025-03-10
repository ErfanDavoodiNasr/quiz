package ir.quiz.quiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class MultipleChoiceQuestionRequest {
    @NotNull
    private String title;

    @NotBlank
    private String questionText;

    @NotNull
    private Long teacherId;

    @NotNull
    private Long courseId;

    @Size(min = 2)
    private List<QuestionOptionRequest> options;
}
