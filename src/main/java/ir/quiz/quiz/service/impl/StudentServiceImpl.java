package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.exception.StudentNotFoundException;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.dto.PersonRequest;
import ir.quiz.quiz.repository.StudentRepository;
import ir.quiz.quiz.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    private static Student personRequestToStudent(PersonRequest personRequest) {
        return Student.builder()
                .firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .password(personRequest.getPassword())
                .username(personRequest.getUsername())
                .status(Status.AWAITING_CONFIRMATION)
                .build();
    }

    @Override
    public Boolean save(PersonRequest studentRequest) {
        Student student = personRequestToStudent(studentRequest);
        Student result = studentRepository.save(student);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Optional<List<Student>> findAllByStatusIsLike(Status status) {
        return studentRepository.findAllByStatusIsLike(status);
    }

    @Override
    public Student update(Student student) {
        if (student == null | student.getId() != null) {
            throw new NullPointerException("student can't be null");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStatus(Long id, Status status) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new StudentNotFoundException("student not found");
        }
        student.get().setStatus(status);
        return studentRepository.save(student.get());
    }
}
