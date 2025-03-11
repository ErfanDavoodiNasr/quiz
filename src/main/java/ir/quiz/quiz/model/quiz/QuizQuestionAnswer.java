package ir.quiz.quiz.model.quiz;

import ir.quiz.quiz.model.BaseModel;
import ir.quiz.quiz.model.Student;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static ir.quiz.quiz.model.quiz.QuizQuestionAnswer.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class QuizQuestionAnswer extends BaseModel<Long> {
    public static final String TABLE_NAME = "answers";

    @Column(nullable = false)
    private String userAnswer;

    @Column
    private Double score;

    @ManyToOne
    @JoinColumn(name = "quiz_question_id")
    private QuizQuestion quizQuestion;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}