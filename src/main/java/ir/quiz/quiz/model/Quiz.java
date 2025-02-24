package ir.quiz.quiz.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.time.LocalDateTime;

import static ir.quiz.quiz.model.Quiz.TABLE_NAME;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class Quiz extends BaseModel<Long> {
    public static final String TABLE_NAME = "quizzes";
    public static final String START_AT = "start_at";
    public static final String END_AT = "end_at";

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(name = START_AT, nullable = false)
    private LocalDateTime startAt;

    @Column(name = END_AT, nullable = false)
    private LocalDateTime endAt;

    @Transient
    private Duration duration = Duration.between(startAt, endAt);

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false, name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(nullable = false, name = "teacher_id")
    private Teacher teacher;
}
