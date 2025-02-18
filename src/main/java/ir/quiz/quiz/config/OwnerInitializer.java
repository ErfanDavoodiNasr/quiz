package ir.quiz.quiz.config;

import ir.quiz.quiz.model.Owner;
import ir.quiz.quiz.service.OwnerService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerInitializer {

    private final OwnerService ownerService;

    @PostConstruct
    public void onStartup() {
        try {
            Boolean result = ownerService.save(Owner.builder().username("admin").password("admin").build());
            if (!result) {
                System.exit(0);
            }
        } catch (Exception e) {
            System.exit(0);
        }
    }

    @PreDestroy
    public void onShutdown() {
        try {
            ownerService.remove(1L);
        } catch (Exception e) {
            System.exit(0);
        }
    }

}