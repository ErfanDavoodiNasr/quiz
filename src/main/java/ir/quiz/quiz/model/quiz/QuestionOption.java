package ir.quiz.quiz.model.quiz;

import ir.quiz.quiz.model.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static ir.quiz.quiz.model.quiz.QuestionOption.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class QuestionOption extends BaseModel<Long> {
    public static final String TABLE_NAME = "options";
    public static final String iS_CORRECT = "is_correct";

    @Column(nullable = false)
    private String text;

    @Column(name = iS_CORRECT, nullable = false)
    private Boolean isCorrect;
}