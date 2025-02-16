package ir.quiz.quiz.controller;

import ir.quiz.quiz.model.*;
import ir.quiz.quiz.model.dto.PersonRequest;
import ir.quiz.quiz.model.dto.PrincipalUpdateRequest;
import ir.quiz.quiz.service.PrincipalService;
import ir.quiz.quiz.service.StudentRegisterService;
import ir.quiz.quiz.service.TeacherRegisterService;
import ir.quiz.quiz.util.ValidatorProvider;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
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
    private final StudentRegisterService studentRegisterService;
    private final TeacherRegisterService teacherRegisterService;

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
    public ResponseEntity<?> getStudentRegisters(@RequestBody Long id) {
        Optional<List<StudentRegister>> result = studentRegisterService.findAllByPrincipal_IdAndStatusIsLike(id, Status.AWAITING_CONFIRMATION);
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body("no request found");
        }
        return ResponseEntity.ok(result.get());
    }


    @PostMapping("/all_teacher_registers")
    public ResponseEntity<?> getTeacherRegisters(@RequestBody Long id) {
        Optional<List<TeacherRegister>> result = teacherRegisterService.findAllByPrincipal_IdAndStatusIsLike(id, Status.AWAITING_CONFIRMATION);
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body("no request found");
        }
        return ResponseEntity.ok(result.get());
    }


    @PostMapping("/update_status")
    public ResponseEntity<?> updateStatus(@RequestBody UpdateStatusDTO updateStatusDTO) {
        Optional<List<String>> constraint = ValidatorProvider.validate(updateStatusDTO);
        if (constraint.isPresent()) {
            return ResponseEntity.status(400).body(constraint.get());
        }
        if (updateStatusDTO.getRole() == UserRole.STUDENT) {
            return studentRegisterService.updateStatus(updateStatusDTO.registerId, updateStatusDTO.getStatus()) ? ResponseEntity.ok("student accept successfully") : ResponseEntity.status(500).body("there is some problem");
        } else if (updateStatusDTO.getRole() == UserRole.TEACHER) {
            return teacherRegisterService.updateStatus(updateStatusDTO.registerId, updateStatusDTO.getStatus()) ? ResponseEntity.ok("teacher accept successfully") : ResponseEntity.status(500).body("there is some problem");
        } else {
            return ResponseEntity.status(404).body("user role not found");
        }
    }


    @Data
    public static class UpdateStatusDTO {
        @NotNull
        private Long registerId;
        @NotNull
        private Status status;
        @NotNull
        private UserRole role;
    }
}
