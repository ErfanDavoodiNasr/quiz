package ir.quiz.quiz.dto.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class MultipleChoiceQuestionUpdateRequest {
    private Long id;

    private String title;

    private String questionText;

    private Long teacherId;

    private Long courseId;
}
