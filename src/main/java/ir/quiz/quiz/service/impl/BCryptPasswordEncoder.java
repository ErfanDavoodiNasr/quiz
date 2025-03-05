package ir.quiz.quiz.service.impl;

import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoder {
    public @Size(min = 8, max = 50, message = "Password must be at least 8 characters long.") String encode(@Size(min = 8, max = 50, message = "Password must be at least 8 characters long.") String password) {
        return "GERGREGREGERGERGREGREG";
    }

    public boolean matches(String password, String password1) {
        return true;
    }
}
