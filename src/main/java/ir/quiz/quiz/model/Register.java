package ir.quiz.quiz.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static ir.quiz.quiz.model.Register.TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TABLE_NAME)
public class Register extends Person {
    public static final String TABLE_NAME = "registers";

    @ManyToOne
    private Principal principal;

    @Enumerated(EnumType.STRING)
    private Role role;
}
