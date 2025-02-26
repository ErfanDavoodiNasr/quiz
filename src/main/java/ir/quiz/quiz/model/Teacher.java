package ir.quiz.quiz.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import static ir.quiz.quiz.model.Teacher.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class Teacher extends User {
    public static final String TABLE_NAME = "teachers";
}
