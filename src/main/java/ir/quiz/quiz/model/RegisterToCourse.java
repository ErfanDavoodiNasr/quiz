package ir.quiz.quiz.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static ir.quiz.quiz.model.RegisterToCourse.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class RegisterToCourse extends Person {
    public static final String TABLE_NAME = "registers";

    @ManyToOne
    private Principal principal;

    @Enumerated(EnumType.STRING)
    private RoleRegisterToCourse roleRegisterToCourse;
}
