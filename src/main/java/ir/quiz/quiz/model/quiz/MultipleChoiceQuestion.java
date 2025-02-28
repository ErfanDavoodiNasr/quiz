package ir.quiz.quiz.model.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
public class MultipleChoiceQuestion extends Question {

    @OneToMany
    @JoinColumn(nullable = false)
    private Set<QuestionOption> questionOptions;
}