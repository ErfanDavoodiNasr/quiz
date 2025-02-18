package ir.quiz.quiz.config;

import ir.quiz.quiz.model.Owner;
import ir.quiz.quiz.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerInitializer implements ApplicationRunner {

    private final OwnerService ownerService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        Owner owner = Owner.builder()
                .username("admin")
                .password(bCryptPasswordEncoder.encode("admin"))
                .build();
        ownerService.save(owner);
    }
}