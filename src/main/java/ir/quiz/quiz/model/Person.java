package ir.quiz.quiz.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.InheritanceType.TABLE_PER_CLASS;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class Person extends BaseModel {

    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Column(name = FIRST_NAME, length = 50)
    private String firstName;

    @Column(name = LAST_NAME, length = 50)
    private String lastName;

    @Column(name = USERNAME, unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = PASSWORD, nullable = false)
    private String password;
}
