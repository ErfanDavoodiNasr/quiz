package ir.quiz.quiz.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class MultipleQuizQuestionResponsePage extends QuizQuestionResponse {
    private Long duration;
    private Integer totalIndex;
    private List<OptionDto> options;


    @Builder
    @Data
    public static class OptionDto {
        private String text;
    }
}
