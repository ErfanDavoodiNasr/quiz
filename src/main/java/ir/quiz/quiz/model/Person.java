package ir.quiz.quiz.model;


import jakarta.persistence.Column;
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
@Inheritance(strategy = TABLE_PER_CLASS)
public class Person<ID extends Number> extends BaseModel<ID> {

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "national_code", unique = true, nullable = false, length = 10)
    private String nationalCode;

    @Column(name = "phone_number", unique = true, nullable = false, length = 11)
    private String phoneNumber;
}
