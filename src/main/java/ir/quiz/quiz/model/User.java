package ir.quiz.quiz.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static jakarta.persistence.InheritanceType.SINGLE_TABLE;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = SINGLE_TABLE)
public abstract class User extends BaseModel<Long> {

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

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @JoinTable(
            name = "fk_users_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
