package ir.quiz.quiz.controller;

import ir.quiz.quiz.dto.request.PersonRequest;
import ir.quiz.quiz.dto.request.StudentUpdateRequest;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.dto.search.StudentSearch;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid PersonRequest personRequest) {
        Boolean result = studentService.save(personRequest);
        return result ? ResponseEntity.ok(new MessageResponse("student saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }


    @PutMapping
    public ResponseEntity<?> update(@RequestBody StudentUpdateRequest studentUpdateRequest) {
        return ResponseEntity.ok(studentService.update(studentUpdateRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestBody StudentSearch studentSearch) {
        List<Student> students = studentService.findAll(studentSearch);
        if (students.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("student not found"));
        }
        return ResponseEntity.ok(students);
    }
}
