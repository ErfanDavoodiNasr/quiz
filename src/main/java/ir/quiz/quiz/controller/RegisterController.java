package ir.quiz.quiz.controller;

import ir.quiz.quiz.dto.request.StudentUpdateStatusRequest;
import ir.quiz.quiz.dto.request.TeacherUpdateStatusRequest;
import ir.quiz.quiz.service.StudentService;
import ir.quiz.quiz.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/registers")
@RequiredArgsConstructor
public class RegisterController {

    private final StudentService studentService;
    private final TeacherService teacherService;


    @PutMapping("/update_student_status")
    public ResponseEntity<?> updateStudentStatus(@RequestBody @Valid StudentUpdateStatusRequest req) {
        return ResponseEntity.ok(studentService.updateStatus(req.getId(), req.getStatus()));
    }

    @PutMapping("/update_teacher_status")
    public ResponseEntity<?> updateTeacherStatus(@RequestBody @Valid TeacherUpdateStatusRequest req) {
        return ResponseEntity.ok(teacherService.updateStatus(req.getId(), req.getStatus()));
    }
}
