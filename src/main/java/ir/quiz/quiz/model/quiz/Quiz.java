package ir.quiz.quiz.model.quiz;


import ir.quiz.quiz.model.BaseModel;
import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Teacher;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

import static ir.quiz.quiz.model.quiz.Quiz.TABLE_NAME;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
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
    private Integer duration;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    private Course course;

    @ManyToMany
    @JoinColumn(nullable = false)
    private Set<QuizQuestion> quizQuestions;
}
