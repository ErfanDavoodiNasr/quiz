package ir.quiz.quiz.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static ir.quiz.quiz.model.Student.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class Student extends User {
    public static final String TABLE_NAME = "students";

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "fk_students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}
