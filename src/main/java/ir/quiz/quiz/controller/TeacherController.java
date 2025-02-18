package ir.quiz.quiz.controller;

import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.dto.request.PersonRequest;
import ir.quiz.quiz.model.dto.search.TeacherSearch;
import ir.quiz.quiz.service.TeacherService;
import ir.quiz.quiz.util.ValidatorProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PersonRequest personRequest) {
        Optional<List<String>> constraint = ValidatorProvider.validate(personRequest);
        if (constraint.isPresent()) {
            return ResponseEntity.status(400).body(constraint.get());
        }
        Boolean result = teacherService.save(personRequest);
        return result ? ResponseEntity.ok("teacher saved successfully") : ResponseEntity.status(500).body("there is some problem please try again later");
    }

    @PostMapping("/update")
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

