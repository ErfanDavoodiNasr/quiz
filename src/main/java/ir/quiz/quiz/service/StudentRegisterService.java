package ir.quiz.quiz.service;

import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.StudentRegister;
import ir.quiz.quiz.model.dto.StudentRegisterRequest;

import java.util.List;
import java.util.Optional;

public interface StudentRegisterService {
    Boolean save(StudentRegisterRequest studentRegisterRequest);

    Optional<List<StudentRegister>> findAllByPrincipal_IdAndStatusIsLike(Long principalId, Status status);
}
