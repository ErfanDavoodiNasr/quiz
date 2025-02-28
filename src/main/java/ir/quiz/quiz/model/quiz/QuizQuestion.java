package ir.quiz.quiz.model.quiz;

import ir.quiz.quiz.model.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
public class QuizQuestion extends BaseModel<Long> {

    @OneToOne
    @JoinColumn(nullable = false)
    private Question questions;

    @Column(columnDefinition = "TEXT")
    private String answer;

    @Column(nullable = false)
    private Double score;
}