package ir.quiz.quiz.model.dto.request;

import ir.quiz.quiz.model.Course;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OwnerUpdateRequest {

    @NotNull
    private Long id;

    @Size(min = 2, max = 50)
    private String firstName;

    @Size(min = 2, max = 50)
    private String lastName;

    @Size(min = 3, max = 50)
    private String username;

    @Size(min = 8, max = 50)
    private String password;

    @NotNull
    private List<Course> courses;
}