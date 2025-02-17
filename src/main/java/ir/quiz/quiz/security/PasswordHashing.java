package ir.quiz.quiz.security;


import org.springframework.stereotype.Component;

@Component
public interface PasswordHashing {
    String hash(String password);
}
