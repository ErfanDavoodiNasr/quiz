package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.dto.PersonRequest;
import ir.quiz.quiz.repository.StudentRepository;
import ir.quiz.quiz.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    private static Student personRequestToStudent(PersonRequest personRequest) {
        return Student.builder()
                .firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .phoneNumber(personRequest.getPhoneNumber())
                .nationalCode(personRequest.getNationalCode())
                .build();
    }

    @Override
    public Boolean save(PersonRequest studentRequest) {
        Student student = personRequestToStudent(studentRequest);
        Student result = studentRepository.save(student);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Student update(Student student) {
        if (student == null | student.getId() != null) {
            throw new NullPointerException("student can't be null");
        }
        return studentRepository.save(student);
    }
}
