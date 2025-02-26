package ir.quiz.quiz.controller;


import ir.quiz.quiz.dto.request.LoginRequest;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.dto.response.OwnerResponse;
import ir.quiz.quiz.dto.response.StudentResponse;
import ir.quiz.quiz.dto.response.TeacherResponse;
import ir.quiz.quiz.model.UserRole;
import ir.quiz.quiz.service.OwnerService;
import ir.quiz.quiz.service.StudentService;
import ir.quiz.quiz.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        if (loginRequest.getUserRole() == UserRole.OWNER) {
            Optional<OwnerResponse> result = ownerService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
            if (result.isEmpty()) {
                return ResponseEntity.status(404).body(new MessageResponse("Owner not found"));
            } else {

            }
        } else if (loginRequest.getUserRole() == UserRole.STUDENT) {
            Optional<StudentResponse> result = studentService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
            if (result.isEmpty()) {
                return ResponseEntity.status(404).body(new MessageResponse("Student not found"));
            } else {

            }
        } else if (loginRequest.getUserRole() == UserRole.TEACHER) {
            Optional<TeacherResponse> result = teacherService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
            if (result.isEmpty()) {
                return ResponseEntity.status(404).body(new MessageResponse("Teacher not found"));
            } else {

            }
        } else {
            return ResponseEntity.status(400).body(new MessageResponse("Role is invalid"));
        }
        return ResponseEntity.ok(new MessageResponse("Logged in successfully"));
    }
}
