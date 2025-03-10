package ir.quiz.quiz.controller;

import ir.quiz.quiz.service.OwnerService;
import ir.quiz.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final OwnerService ownerService;

    @PostMapping("/owner")
    public ResponseEntity<?> ownerLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        return ResponseEntity.ok(ownerService.login(username, password));
    }

    @PostMapping
    public ResponseEntity<?> usersLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        return ResponseEntity.ok(userService.login(username, password));
    }

}
