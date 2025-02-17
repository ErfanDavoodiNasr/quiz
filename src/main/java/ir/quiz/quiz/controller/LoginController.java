package ir.quiz.quiz.controller;


import ir.quiz.quiz.model.Owner;
import ir.quiz.quiz.model.UserRole;
import ir.quiz.quiz.model.dto.LoginRequest;
import ir.quiz.quiz.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getUserRole() == UserRole.OWNER) {
            Optional<Owner> result = ownerService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
            if (result.isEmpty()) {
                return ResponseEntity.status(404).body("owner not found");
            }
        }
        return null;
    }
}
