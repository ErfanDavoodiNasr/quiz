package ir.quiz.quiz.dto.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuizQuestionUpdateRequest {
    private Long id;
    private Long questionId;
    private Double score;
}
