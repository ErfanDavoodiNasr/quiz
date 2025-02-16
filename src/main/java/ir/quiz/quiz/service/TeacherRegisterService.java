package ir.quiz.quiz.service;

import ir.quiz.quiz.model.TeacherRegister;
import ir.quiz.quiz.model.dto.TeacherRegisterRequest;

import java.util.List;
import java.util.Optional;

public interface TeacherRegisterService {
    Boolean save(TeacherRegisterRequest teacherRegisterRequest);

    Optional<List<TeacherRegister>> findAllByPrincipalId(Long id);
}
