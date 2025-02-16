package ir.quiz.quiz.service;

import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.dto.PersonRequest;

public interface TeacherService {
    Boolean save(PersonRequest teacherRequest);

    Teacher update(Teacher teacher);
}
