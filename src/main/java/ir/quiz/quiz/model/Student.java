package ir.quiz.quiz.model;


import jakarta.persistence.*;

import java.util.List;

import static ir.quiz.quiz.model.Student.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
public class Student extends Person<Long> {
    public static final String TABLE_NAME = "students";

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "fk_students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}
