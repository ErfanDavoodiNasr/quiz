package ir.quiz.quiz.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class QuizUpdateRequest {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String startAt;

    @NotBlank
    private String endAt;
}