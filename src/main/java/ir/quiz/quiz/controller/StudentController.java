package ir.quiz.quiz.controller;

import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.dto.PersonRequest;
import ir.quiz.quiz.service.StudentService;
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
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PersonRequest personRequest) {
        Optional<List<String>> constraint = ValidatorProvider.validate(personRequest);
        if (constraint.isPresent()) {
            return ResponseEntity.status(400).body(constraint.get());
        }
        Boolean result = studentService.save(personRequest);
        return result ? ResponseEntity.ok("student saved successfully") : ResponseEntity.status(500).body("there is some problem please try again later");
    }


    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.update(student));
    }

}
