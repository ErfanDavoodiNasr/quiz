package ir.quiz.quiz.dto.response;

import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class StudentResponse extends UserResponse {
    private List<Course> courses;
    private Status status;
}
