package ir.quiz.quiz.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class QuizQuestionResponse {
    private Long id;
    private QuestionResponse question;
    private Double score;
}
