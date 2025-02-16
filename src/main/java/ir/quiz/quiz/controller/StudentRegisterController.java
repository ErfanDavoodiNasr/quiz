package ir.quiz.quiz.controller;


import ir.quiz.quiz.model.dto.StudentRegisterRequest;
import ir.quiz.quiz.service.StudentRegisterService;
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
@RequestMapping("api/student_register")
@RequiredArgsConstructor
public class StudentRegisterController {

    private final StudentRegisterService studentRegisterService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody StudentRegisterRequest studentRegisterRequest) {
        Optional<List<String>> constraint = ValidatorProvider.validate(studentRegisterRequest);
        if (constraint.isPresent()) {
            return ResponseEntity.status(400).body(constraint.get());
        }
        Boolean result = studentRegisterService.save(studentRegisterRequest);
        return result ? ResponseEntity.ok("register request saved successfully") : ResponseEntity.status(500).body("there is some problem please try again later");
    }
}
