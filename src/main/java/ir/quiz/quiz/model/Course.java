package ir.quiz.quiz.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

import static ir.quiz.quiz.model.Course.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class Course extends BaseModel<Long> {
    public static final String TABLE_NAME = "courses";

    @Column(length = 50)
    private String name;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "courses")
    private List<Student> students;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Principal principal;
}
