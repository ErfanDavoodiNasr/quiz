package ir.quiz.quiz.config;

import ir.quiz.quiz.dto.response.OwnerResponse;
import ir.quiz.quiz.model.User;
import ir.quiz.quiz.repository.OwnerRepository;
import ir.quiz.quiz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            Optional<OwnerResponse> owner = ownerRepository.findByUsername(username);
            if (user.isEmpty()) {
                if (owner.isPresent()) {
                    return org.springframework.security.core.userdetails.User.builder().password(owner.get().getPassword()).authorities(new SimpleGrantedAuthority("ROLE_OWNER")).username("admin").build();
                }
            } else {
                throw new UsernameNotFoundException("no user found");
            }

        }
        return user.get();
    }

}