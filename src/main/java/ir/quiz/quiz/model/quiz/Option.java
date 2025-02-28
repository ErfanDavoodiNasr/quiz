package ir.quiz.quiz.model.quiz;

import ir.quiz.quiz.model.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static ir.quiz.quiz.model.quiz.Option.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class Option extends BaseModel<Long> {
    public static final String TABLE_NAME = "options";

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Boolean isCorrect;
}