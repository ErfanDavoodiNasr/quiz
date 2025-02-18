package ir.quiz.quiz.service;

import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.dto.request.PersonRequest;
import ir.quiz.quiz.model.dto.response.TeacherResponse;
import ir.quiz.quiz.model.dto.search.TeacherSearch;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    Boolean save(PersonRequest teacherRequest);

    Optional<List<Teacher>> findAllByStatusIsLike(Status status);

    Teacher update(Teacher teacher);

    Teacher updateStatus(Long id, Status status);

    Optional<Teacher> findById(Long id);

    Optional<TeacherResponse> findByUsernameAndPassword(String username, String password);

    List<Teacher> findAll(TeacherSearch search);
}