package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.PersonRequest;
import ir.quiz.quiz.dto.request.TeacherUpdateRequest;
import ir.quiz.quiz.dto.response.JwtTokenResponse;
import ir.quiz.quiz.dto.search.TeacherSearch;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Teacher;

import java.util.List;

public interface TeacherService {
    Boolean save(PersonRequest teacherRequest);

    Teacher update(TeacherUpdateRequest teacherUpdateRequest);

    Teacher updateStatus(Long id, Status status);

    List<Teacher> findAll(TeacherSearch search);
}