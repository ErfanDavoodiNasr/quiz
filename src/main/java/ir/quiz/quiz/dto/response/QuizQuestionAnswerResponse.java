package ir.quiz.quiz.dto.response;


import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.quiz.QuizQuestion;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
