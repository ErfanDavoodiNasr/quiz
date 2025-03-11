package ir.quiz.quiz.model.quiz;


import ir.quiz.quiz.model.BaseModel;
import ir.quiz.quiz.model.Student;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static ir.quiz.quiz.model.quiz.StudentInQuiz.TABLE_NAME;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class StudentInQuiz extends BaseModel<Long> {
    public static final String TABLE_NAME = "students_in_quiz";
    public static final String IS_SUBMIT = "is_submit";

    @ManyToOne
    private Student student;

    @Column(name = IS_SUBMIT)
    private Boolean isSubmit;
}
