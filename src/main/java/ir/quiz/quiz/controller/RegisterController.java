package ir.quiz.quiz.controller;

import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.dto.request.UpdateStudentStatusRequest;
import ir.quiz.quiz.model.dto.request.UpdateTeacherStatusRequest;
import ir.quiz.quiz.service.StudentService;
import ir.quiz.quiz.service.TeacherService;
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
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final StudentService studentService;
    private final TeacherService teacherService;

    @PostMapping("/all_student_registers")
    public ResponseEntity<?> getStudentRegisters() {
        Optional<List<Student>> result = studentService.findAllByStatusIsLike(Status.AWAITING_CONFIRMATION);
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body("no request found");
        }
        return ResponseEntity.ok(result.get());
    }


    @PostMapping("/all_teacher_registers")
    public ResponseEntity<?> getTeacherRegisters() {
        Optional<List<Teacher>> result = teacherService.findAllByStatusIsLike(Status.AWAITING_CONFIRMATION);
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body("no request found");
        }
        return ResponseEntity.ok(result.get());
    }


    @PostMapping("/update_student_status")
    public ResponseEntity<?> updateStudentStatus(@RequestBody UpdateStudentStatusRequest req) {
        Optional<List<String>> constraint = ValidatorProvider.validate(req);
        if (constraint.isPresent()) {
            return ResponseEntity.status(400).body(constraint.get());
        }
        return ResponseEntity.ok(studentService.updateStatus(req.getId(), req.getStatus()));
    }

    @PostMapping("/update_teacher_status")
    public ResponseEntity<?> updateTeacherStatus(@RequestBody UpdateTeacherStatusRequest req) {
        Optional<List<String>> constraint = ValidatorProvider.validate(req);
        if (constraint.isPresent()) {
            return ResponseEntity.status(400).body(constraint.get());
        }
        return ResponseEntity.ok(teacherService.updateStatus(req.getId(), req.getStatus()));
    }
}
