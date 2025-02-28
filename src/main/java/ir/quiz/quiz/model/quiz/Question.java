package ir.quiz.quiz.model.quiz;

import ir.quiz.quiz.model.BaseModel;
import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Teacher;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static ir.quiz.quiz.model.quiz.Question.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Question extends BaseModel<Long> {

    public static final String TABLE_NAME = "questions";
    public static final String QUESTION_TEXT = "question_text";

    @Column(nullable = false , length = 50)
    private String title;

    @Column(name = QUESTION_TEXT, nullable = false)
    private String questionText;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;
}