package ir.quiz.quiz.controller;


import ir.quiz.quiz.dto.request.LoginRequest;
import ir.quiz.quiz.dto.response.OwnerResponse;
import ir.quiz.quiz.dto.response.StudentResponse;
import ir.quiz.quiz.dto.response.TeacherResponse;
import ir.quiz.quiz.dto.response.UserResponse;
import ir.quiz.quiz.model.UserRole;
import ir.quiz.quiz.service.OwnerService;
import ir.quiz.quiz.service.StudentService;
import ir.quiz.quiz.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final OwnerService ownerService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    private static <T extends UserResponse> void authentication(HttpServletRequest request, T person, String role) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                person.getUsername(),
                null,
                List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest request) {
        if (loginRequest.getUserRole() == UserRole.OWNER) {
            Optional<OwnerResponse> result = ownerService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
            if (result.isEmpty()) {
                return ResponseEntity.status(404).body("Owner not found");
            } else {
                authentication(request, result.get(), "OWNER");
            }
        } else if (loginRequest.getUserRole() == UserRole.STUDENT) {
            Optional<StudentResponse> result = studentService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
            if (result.isEmpty()) {
                return ResponseEntity.status(404).body("Student not found");
            } else {
                authentication(request, result.get(), "STUDENT");
            }
        } else if (loginRequest.getUserRole() == UserRole.TEACHER) {
            Optional<TeacherResponse> result = teacherService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
            if (result.isEmpty()) {
                return ResponseEntity.status(404).body("Teacher not found");
            } else {
                authentication(request, result.get(), "TEACHER");
            }
        } else {
            return ResponseEntity.status(400).body("Role is invalid");
        }
        return ResponseEntity.ok("Logged in successfully");
    }
}
