package ir.quiz.quiz.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Builder
@Data
public class CourseRequest {

    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    private String startAt;

    @NotNull
    private String endAt;
}
