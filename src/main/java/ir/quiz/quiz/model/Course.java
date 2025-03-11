package ir.quiz.quiz.model;


import ir.quiz.quiz.model.quiz.Quiz;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

import static ir.quiz.quiz.model.Course.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class Course extends BaseModel<Long> {

    public static final String TABLE_NAME = "courses";
    public static final String START_AT = "start_at";
    public static final String END_AT = "end_at";
    @OneToMany(mappedBy = "course")
    List<Quiz> quizzes;
    @Column(length = 50)
    private String name;
    @Column(name = START_AT, nullable = false)
    private LocalDateTime startAt;
    @Column(name = END_AT, nullable = false)
    private LocalDateTime endAt;
    @ManyToMany
    private List<Student> students;
    @ManyToOne
    private Teacher teacher;
}