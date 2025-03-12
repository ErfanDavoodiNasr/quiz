package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.config.JwtService;
import ir.quiz.quiz.dto.response.JwtTokenResponse;
import ir.quiz.quiz.exception.AwaitingConfirmationException;
import ir.quiz.quiz.exception.UserNotFoundException;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.User;
import ir.quiz.quiz.repository.UserRepository;
import ir.quiz.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private static void checkUserIsAccepted(Optional<User> user) {
        if (user.get().getStatus() != Status.ACCEPTED) {
            throw new AwaitingConfirmationException("user status is " + user.get().getStatus());
        }
    }

    private static void checkUserIsExist(Optional<User> user) {
        if (user.isEmpty()) {
            throw new UserNotFoundException("no user found");
        }
    }

    @Override
    public JwtTokenResponse login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        checkUserIsExist(user);
        checkUserIsAccepted(user);
        if (passwordEncoder.matches(password, user.get().getPassword())) {
            return new JwtTokenResponse(jwtService.generateJwtToken(user.get()));
        }
        throw new RuntimeException("username or password is wrong");
    }

}
