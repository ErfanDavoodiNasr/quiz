package ir.quiz.quiz.model.quiz;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
public class MultipleChoiceQuestion extends Question {

    @OneToMany(mappedBy = "multipleChoiceQuestion", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<QuestionOption> options;
}