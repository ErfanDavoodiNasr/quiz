package ir.quiz.quiz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static ir.quiz.quiz.model.Principal.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class Principal extends Person {
    public static final String TABLE_NAME = "principals";
}
