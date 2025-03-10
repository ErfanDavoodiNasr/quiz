package ir.quiz.quiz.dto.request;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class QuestionOptionUpdateRequest {
    private Long id;

    private String text;

    private Boolean isCorrect;

    private Long multipleChoiceQuestionId;
}
