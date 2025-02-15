package ir.quiz.quiz.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static ir.quiz.quiz.model.Teacher.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class Teacher extends Person {
    public static final String TABLE_NAME = "teachers";

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "teacher")
    private List<Course> courses;
}
