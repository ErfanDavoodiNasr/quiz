package ir.quiz.quiz.controller;

import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.dto.request.PersonRequest;
import ir.quiz.quiz.dto.search.TeacherSearch;
import ir.quiz.quiz.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid PersonRequest personRequest) {
        Boolean result = teacherService.save(personRequest);
        return result ? ResponseEntity.ok("teacher saved successfully") : ResponseEntity.status(500).body("there is some problem please try again later");
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Teacher teacher) {
        if (teacher == null) {
            return ResponseEntity.status(400).body("teacher is null");
        }
        return ResponseEntity.ok(teacherService.update(teacher));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestBody TeacherSearch teacherSearch) {
        List<Teacher> teachers = teacherService.findAll(teacherSearch);
        if (teachers.isEmpty()) {
            return ResponseEntity.status(404).body("teacher not found");
        }
        return ResponseEntity.ok(teachers);
    }

}

