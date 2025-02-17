package ir.quiz.quiz.service;

import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.dto.PersonRequest;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Boolean save(PersonRequest studentRequest);

    Optional<List<Student>> findAllByStatusIsLike(Status status);

    Student update(Student student);

    Student updateStatus(Long id, Status status);

    Optional<Student> findById(Long id);

    Optional<Student> findByUsernameAndPassword(String username, String password);
}
