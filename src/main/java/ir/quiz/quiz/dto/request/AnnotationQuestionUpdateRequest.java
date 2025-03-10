package ir.quiz.quiz.dto.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
public class AnnotationQuestionUpdateRequest {
    private Long id;

    private String title;

    private String questionText;

    private Long teacherId;

    private Long courseId;
}
