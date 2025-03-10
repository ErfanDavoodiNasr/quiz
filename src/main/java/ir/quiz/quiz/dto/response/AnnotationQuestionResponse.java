package ir.quiz.quiz.dto.response;


import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class AnnotationQuestionResponse {
    private Long id;

    private String title;

    private String questionText;

    private TeacherResponse teacher;

    private CourseResponse course;
}
