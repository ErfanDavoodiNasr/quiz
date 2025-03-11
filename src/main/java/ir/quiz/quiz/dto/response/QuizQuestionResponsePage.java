package ir.quiz.quiz.dto.response;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class QuizQuestionResponsePage extends QuizQuestionResponse {
    private Long duration;
    private Integer totalIndex;
}
