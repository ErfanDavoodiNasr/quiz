package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.config.JwtService;
import ir.quiz.quiz.dto.response.JwtTokenResponse;
import ir.quiz.quiz.dto.response.OwnerResponse;
import ir.quiz.quiz.exception.OwnerNotFoundException;
import ir.quiz.quiz.exception.UserNotFoundException;
import ir.quiz.quiz.repository.OwnerRepository;
import ir.quiz.quiz.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public JwtTokenResponse login(String username, String password) {
        Optional<OwnerResponse> user = checkOwnerIsExist(username);
        if (passwordEncoder.matches(password, user.get().getPassword())) {
            return new JwtTokenResponse(jwtService.generateJwtToken(User.builder().username(username).password(password).authorities(new SimpleGrantedAuthority("ROLE_OWNER")).build()));
        }
        throw new UserNotFoundException("username or password is wrong");
    }

    private Optional<OwnerResponse> checkOwnerIsExist(String username) {
        Optional<OwnerResponse> owner = ownerRepository.findByUsername(username);
        if (owner.isEmpty()) {
            throw new OwnerNotFoundException("owner not found");
        }
        return owner;
    }
}