package ir.quiz.quiz.service;

import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.dto.PersonRequest;

public interface StudentService {
    Boolean save(PersonRequest studentRequest);

    Student update(Student student);
}
