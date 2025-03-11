package ir.quiz.quiz.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Data
public class CourseStudentResponse {
    private Long id;

    private String name;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private TeacherResponse teacher;

    private List<QuizResponse> quizzes;
}
