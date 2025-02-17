package ir.quiz.quiz.owner_initializer;

import ir.quiz.quiz.model.Owner;
import ir.quiz.quiz.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerInitializer implements ApplicationRunner {

    private final OwnerService ownerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            Owner owner = Owner.builder()
                    .username("admin")
                    .password("admin")
                    .build();

            if (!ownerService.save(owner)) {

            }
        } catch (Exception e) {

        }
    }
}
