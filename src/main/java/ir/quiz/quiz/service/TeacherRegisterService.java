package ir.quiz.quiz.service;

import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.TeacherRegister;
import ir.quiz.quiz.model.dto.TeacherRegisterRequest;

import java.util.List;
import java.util.Optional;

public interface TeacherRegisterService {
    Boolean save(TeacherRegisterRequest teacherRegisterRequest);

    Boolean updateStatus(Long id, Status status);

    Optional<List<TeacherRegister>> findAllByPrincipal_IdAndStatusIsLike(Long principalId, Status status);

    ;
}
