package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.exception.TeacherNotFoundException;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.dto.PersonRequest;
import ir.quiz.quiz.repository.StudentRepository;
import ir.quiz.quiz.repository.TeacherRepository;
import ir.quiz.quiz.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    private static Teacher personRequestToTeacher(PersonRequest personRequest) {
        return Teacher.builder()
                .firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .username(personRequest.getUsername())
                .password(personRequest.getPassword())
                .status(Status.AWAITING_CONFIRMATION)
                .build();
    }

    @Override
    public Boolean save(PersonRequest teacherRequest) {
        Teacher teacher = personRequestToTeacher(teacherRequest);
        Teacher result = teacherRepository.save(teacher);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Optional<List<Teacher>> findAllByStatusIsLike(Status status) {
        return teacherRepository.findAllByStatusIsLike(status);
    }

    @Override
    public Teacher update(Teacher teacher) {
        if (teacher == null || teacher.getId() == null) {
            throw new NullPointerException("teacher can't be null");
        }
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateStatus(Long id, Status status) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) {
            throw new TeacherNotFoundException("teacher not found");
        }
        teacher.get().setStatus(status);
        return teacherRepository.save(teacher.get());
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Optional<Teacher> findByUsernameAndPassword(String username, String password) {
        return teacherRepository.findByUsernameAndPassword(username, password);
    }

}
