package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.dto.PersonRequest;
import ir.quiz.quiz.repository.TeacherRepository;
import ir.quiz.quiz.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private static Teacher personRequestToTeacher(PersonRequest personRequest) {
        return Teacher.builder()
                .firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .phoneNumber(personRequest.getPhoneNumber())
                .nationalCode(personRequest.getNationalCode())
                .build();
    }

    @Override
    public Boolean save(PersonRequest teacherRequest) {
        Teacher teacher = personRequestToTeacher(teacherRequest);
        Teacher result = teacherRepository.save(teacher);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Teacher update(Teacher teacher) {
        if (teacher == null || teacher.getId() != null) {
            throw new NullPointerException("teacher can't be null");
        }
        return teacherRepository.save(teacher);
    }

}
