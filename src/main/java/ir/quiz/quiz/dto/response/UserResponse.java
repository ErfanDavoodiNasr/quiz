package ir.quiz.quiz.dto.response;

import ir.quiz.quiz.model.Permission;
import ir.quiz.quiz.model.Role;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private List<Permission> permissions;
    private Role role;
}
