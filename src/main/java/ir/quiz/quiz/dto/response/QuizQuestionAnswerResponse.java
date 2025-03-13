package ir.quiz.quiz.dto.response;


import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class QuizQuestionAnswerResponse {
    private Long id;

    private String userAnswer;

    private Double score;

    private QuizQuestionResponse quizQuestion;

    private StudentResponse student;
}
