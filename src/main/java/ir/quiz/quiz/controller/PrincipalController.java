package ir.quiz.quiz.controller;

import ir.quiz.quiz.model.Principal;
import ir.quiz.quiz.model.dto.PersonRequest;
import ir.quiz.quiz.model.dto.PrincipalUpdateRequest;
import ir.quiz.quiz.service.PrincipalService;
import ir.quiz.quiz.util.ValidatorProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/principal")
@RequiredArgsConstructor
public class PrincipalController {

    private final PrincipalService principalService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PersonRequest personRequest) {
        Optional<List<String>> constraint = ValidatorProvider.validate(personRequest);
        if (constraint.isPresent()) {
            return ResponseEntity.status(500).body(constraint.get());
        }
        Boolean result = principalService.save(personRequest);
        return result ? ResponseEntity.ok("principal saved successfully") : ResponseEntity.status(500).body("there is some problem please try again later");
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody PrincipalUpdateRequest principalUpdateRequest) {
        Optional<List<String>> constraint = ValidatorProvider.validate(principalUpdateRequest);
        if (constraint.isPresent()) {
            return ResponseEntity.status(500).body(constraint.get());
        }
        Principal result = principalService.update(principalUpdateRequest);
        return ResponseEntity.ok(result);
    }

}
