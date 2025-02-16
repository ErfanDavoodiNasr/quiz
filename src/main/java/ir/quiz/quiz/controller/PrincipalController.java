package ir.quiz.quiz.controller;

import ir.quiz.quiz.model.Principal;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.dto.PersonRequest;
import ir.quiz.quiz.model.dto.PrincipalUpdateRequest;
import ir.quiz.quiz.model.dto.UpdateStudentStatusRequest;
import ir.quiz.quiz.model.dto.UpdateTeacherStatusRequest;
import ir.quiz.quiz.service.PrincipalService;
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
@RequestMapping("/api/principal")
@RequiredArgsConstructor
public class PrincipalController {

    private final PrincipalService principalService;
    private final StudentService studentService;
    private final TeacherService teacherService;


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PersonRequest personRequest) {
        Optional<List<String>> constraint = ValidatorProvider.validate(personRequest);
        if (constraint.isPresent()) {
            return ResponseEntity.status(400).body(constraint.get());
        }
        Boolean result = principalService.save(personRequest);
        return result ? ResponseEntity.ok("principal saved successfully") : ResponseEntity.status(500).body("there is some problem please try again later");
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody PrincipalUpdateRequest principalUpdateRequest) {
        Optional<List<String>> constraint = ValidatorProvider.validate(principalUpdateRequest);
        if (constraint.isPresent()) {
            return ResponseEntity.status(400).body(constraint.get());
        }
        Principal result = principalService.update(principalUpdateRequest);
        return ResponseEntity.ok(result);
    }


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
