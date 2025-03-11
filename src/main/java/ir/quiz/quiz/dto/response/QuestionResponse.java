package ir.quiz.quiz.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class QuestionResponse {

    private Long id;

    private String title;

    private String questionText;
}
