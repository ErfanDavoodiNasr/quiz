package ir.quiz.quiz.controller;

import ir.quiz.quiz.dto.request.PersonRequest;
import ir.quiz.quiz.dto.request.TeacherUpdateRequest;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.dto.search.TeacherSearch;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid PersonRequest personRequest) {
        Boolean result = teacherService.save(personRequest);
        return result ? ResponseEntity.ok(new MessageResponse("teacher saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid TeacherUpdateRequest teacherUpdateRequest) {
        return ResponseEntity.ok(teacherService.update(teacherUpdateRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestBody TeacherSearch teacherSearch) {
        List<Teacher> teachers = teacherService.findAll(teacherSearch);
        if (teachers.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("teacher not found"));
        }
        return ResponseEntity.ok(teachers);
    }
}