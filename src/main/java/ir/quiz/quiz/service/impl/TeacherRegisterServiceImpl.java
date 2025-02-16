package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.model.Principal;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.TeacherRegister;
import ir.quiz.quiz.model.dto.TeacherRegisterRequest;
import ir.quiz.quiz.repository.TeacherRegisterRepository;
import ir.quiz.quiz.service.TeacherRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TeacherRegisterServiceImpl implements TeacherRegisterService {

    private final TeacherRegisterRepository teacherRegisterRepository;

    private static TeacherRegister teacherRegisterRequestToTeacherRegister(TeacherRegisterRequest teacherRegisterRequest) {
        return TeacherRegister.builder()
                .principal(Principal.builder().id(teacherRegisterRequest.getTeacherId()).build())
                .teacher(Teacher.builder().id(teacherRegisterRequest.getTeacherId()).build())
                .build();
    }

    @Override
    public Boolean save(TeacherRegisterRequest teacherRegisterRequest) {
        TeacherRegister teacherRegister = teacherRegisterRequestToTeacherRegister(teacherRegisterRequest);
        TeacherRegister result = teacherRegisterRepository.save(teacherRegister);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Optional<List<TeacherRegister>> findAllByPrincipalId(Long id) {
        return teacherRegisterRepository.findAllByPrincipal_Id(id);
    }
}
