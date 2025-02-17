package ir.quiz.quiz.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BcryptPasswordHashing implements PasswordHashing {

    @Override
    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
