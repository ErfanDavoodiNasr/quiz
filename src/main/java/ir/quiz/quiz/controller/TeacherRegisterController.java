package ir.quiz.quiz.controller;


import ir.quiz.quiz.model.dto.TeacherRegisterRequest;
import ir.quiz.quiz.service.TeacherRegisterService;
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
@RequestMapping("api/teacher_register")
@RequiredArgsConstructor
public class TeacherRegisterController {

    private final TeacherRegisterService teacherRegisterService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TeacherRegisterRequest teacherRegisterRequest) {
        Optional<List<String>> constraint = ValidatorProvider.validate(teacherRegisterRequest);
        if (constraint.isPresent()) {
            return ResponseEntity.status(400).body(constraint.get());
        }
        Boolean result = teacherRegisterService.save(teacherRegisterRequest);
        return result ? ResponseEntity.ok("register request saved successfully") : ResponseEntity.status(500).body("there is some problem please try again later");
    }
}
