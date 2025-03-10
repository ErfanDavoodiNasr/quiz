package ir.quiz.quiz.dto.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuestionOptionResponse {
    private String text;

    private Boolean isCorrect;

    private QuestionResponse multipleChoiceQuestion;
}
