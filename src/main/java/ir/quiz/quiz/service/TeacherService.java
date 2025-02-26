package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.PersonRequest;
import ir.quiz.quiz.dto.request.TeacherUpdateRequest;
import ir.quiz.quiz.dto.response.TeacherResponse;
import ir.quiz.quiz.dto.search.TeacherSearch;
import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    Boolean save(PersonRequest teacherRequest);

    Teacher update(TeacherUpdateRequest teacherUpdateRequest);

    Teacher updateStatus(Long id, Status status);

    Optional<TeacherResponse> findByUsernameAndPassword(String username, String password);

    Optional<Teacher> findReferenceById(Long id);

    Optional<List<Course>> findTeacherCourses(Long teacherId);

    List<Teacher> findAll(TeacherSearch search);
}