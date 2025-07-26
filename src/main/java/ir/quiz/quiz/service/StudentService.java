package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.PersonRequest;
import ir.quiz.quiz.dto.request.StudentUpdateRequest;
import ir.quiz.quiz.dto.response.StudentResponse;
import ir.quiz.quiz.dto.search.StudentSearch;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Boolean save(PersonRequest studentRequest);

    Student update(StudentUpdateRequest studentUpdateRequest);

    Student updateStatus(Long id, Status status);

    Optional<Student> findById(Long id);

    Optional<StudentResponse> findByUsernameAndPassword(String username, String password);

    Optional<Student> findReferenceById(Long id);

    List<Student> findAll(StudentSearch search);
}