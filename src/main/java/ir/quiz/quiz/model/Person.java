package ir.quiz.quiz.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.InheritanceType.TABLE_PER_CLASS;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class Person extends BaseModel {

    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

    @Column(name = FIRST_NAME, length = 50)
    private String firstName;

    @Column(name = LAST_NAME, length = 50)
    private String lastName;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;
}
