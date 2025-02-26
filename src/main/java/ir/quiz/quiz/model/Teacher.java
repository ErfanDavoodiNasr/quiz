package ir.quiz.quiz.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static ir.quiz.quiz.model.Teacher.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class Teacher extends User {
    public static final String TABLE_NAME = "teachers";

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "teacher", fetch = FetchType.EAGER)
    private List<Course> courses;
}
