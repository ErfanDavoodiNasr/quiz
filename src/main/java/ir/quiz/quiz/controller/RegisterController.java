package ir.quiz.quiz.controller;

import ir.quiz.quiz.dto.request.StudentUpdateStatusRequest;
import ir.quiz.quiz.dto.request.TeacherUpdateStatusRequest;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.service.StudentService;
import ir.quiz.quiz.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/registers")
@RequiredArgsConstructor
public class RegisterController {

    private final StudentService studentService;
    private final TeacherService teacherService;

    @GetMapping("/all_student_registers")
    public ResponseEntity<?> getStudentRegisters() {
        Optional<List<Student>> result = studentService.findAllByStatusIsLike(Status.AWAITING_CONFIRMATION);
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body("no request found");
        }
        return ResponseEntity.ok(result.get());
    }


    @GetMapping("/all_teacher_registers")
    public ResponseEntity<?> getTeacherRegisters() {
        Optional<List<Teacher>> result = teacherService.findAllByStatusIsLike(Status.AWAITING_CONFIRMATION);
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body("no request found");
        }
        return ResponseEntity.ok(result.get());
    }


    @PutMapping("/update_student_status")
    public ResponseEntity<?> updateStudentStatus(@RequestBody @Valid StudentUpdateStatusRequest req) {
        return ResponseEntity.ok(studentService.updateStatus(req.getId(), req.getStatus()));
    }

    @PutMapping("/update_teacher_status")
    public ResponseEntity<?> updateTeacherStatus(@RequestBody @Valid TeacherUpdateStatusRequest req) {
        return ResponseEntity.ok(teacherService.updateStatus(req.getId(), req.getStatus()));
    }
}
