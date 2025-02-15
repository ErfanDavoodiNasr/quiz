package ir.quiz.quiz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

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

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "principal")
    private List<Course> courses;
}
