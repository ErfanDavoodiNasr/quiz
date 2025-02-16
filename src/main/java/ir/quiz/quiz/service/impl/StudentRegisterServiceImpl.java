package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.model.Principal;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.StudentRegister;
import ir.quiz.quiz.model.dto.StudentRegisterRequest;
import ir.quiz.quiz.repository.StudentRegisterRepository;
import ir.quiz.quiz.service.StudentRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StudentRegisterServiceImpl implements StudentRegisterService {

    private final StudentRegisterRepository studentRegisterRepository;

    private static StudentRegister studentRegisterRequestToStudentRegister(StudentRegisterRequest studentRegisterRequest) {
        return StudentRegister.builder()
                .student(Student.builder().id(studentRegisterRequest.getStudentId()).build())
                .principal(Principal.builder().id(studentRegisterRequest.getPrincipalId()).build())
                .build();
    }

    @Override
    public Boolean save(StudentRegisterRequest studentRegisterRequest) {
        StudentRegister studentRegister = studentRegisterRequestToStudentRegister(studentRegisterRequest);
        StudentRegister result = studentRegisterRepository.save(studentRegister);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Optional<List<StudentRegister>> findAllByPrincipal_IdAndStatusIsLike(Long principalId, Status status) {
        return studentRegisterRepository.findAllByPrincipal_IdAndStatusIsLike(principalId, status);
    }
}
