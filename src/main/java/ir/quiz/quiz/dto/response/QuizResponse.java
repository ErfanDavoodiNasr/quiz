package ir.quiz.quiz.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Builder
@Data
public class QuizResponse {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private Long duration;
}
