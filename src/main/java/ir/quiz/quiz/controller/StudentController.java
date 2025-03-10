package ir.quiz.quiz.controller;

import ir.quiz.quiz.dto.request.PersonRequest;
import ir.quiz.quiz.dto.request.StudentUpdateRequest;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.dto.search.StudentSearch;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid PersonRequest personRequest) {
        return ResponseEntity.ok(studentService.save(personRequest));
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
