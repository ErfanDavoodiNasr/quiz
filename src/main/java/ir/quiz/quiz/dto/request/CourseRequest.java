package ir.quiz.quiz.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class CourseRequest {

    @Size(min = 2, max = 50, message = "Course name must be between 2 and 50 characters.")
    private String name;

    @NotNull(message = "Start date is required.")
    private String startAt;

    @NotNull(message = "End date is required.")
    private String endAt;
}