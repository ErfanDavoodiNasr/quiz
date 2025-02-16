package ir.quiz.quiz.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static ir.quiz.quiz.model.Teacher.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class StudentRegister extends BaseModel {
    public static final String TABLE_NAME = "student_registers";

    @ManyToOne
    private Student student;

    @ManyToOne
    private Principal principal;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.AWAITING_CONFIRMATION;
}
