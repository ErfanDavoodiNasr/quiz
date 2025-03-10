package ir.quiz.quiz.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class MultipleChoiceQuestionResponse extends QuestionResponse {
    private List<QuestionOptionResponse> options;
}
