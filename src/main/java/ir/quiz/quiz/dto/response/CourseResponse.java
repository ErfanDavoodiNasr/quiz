package ir.quiz.quiz.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Data
public class CourseResponse {
    private Long id;

    private String name;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private List<StudentResponse> students;

    private TeacherResponse teacher;
}
