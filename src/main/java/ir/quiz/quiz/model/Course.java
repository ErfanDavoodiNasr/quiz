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
public class Course extends BaseModel {

    public static final String TABLE_NAME = "courses";
    public static final String NAME = "name";
    public static final String START_AT = "start_at";
    public static final String END_AT = "end_at";

    @Column(name = NAME, length = 50)
    private String name;

    @Column(name = START_AT, nullable = false)
    private LocalDateTime startAt;

    @Column(name = END_AT, nullable = false)
    private LocalDateTime endAt;

    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE}, mappedBy = "courses")
    private List<Student> students;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private Teacher teacher;
}
