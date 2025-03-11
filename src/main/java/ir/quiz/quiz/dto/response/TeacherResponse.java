package ir.quiz.quiz.dto.response;

import ir.quiz.quiz.model.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class TeacherResponse extends UserResponse {
    private Status status;
}
