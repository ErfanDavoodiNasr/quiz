package ir.quiz.quiz.model.dto.response;

import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class TeacherResponse extends UserResponse {
    private List<Course> courses;
    private Status status;
}
