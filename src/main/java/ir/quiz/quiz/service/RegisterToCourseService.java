package ir.quiz.quiz.service;

import ir.quiz.quiz.model.RegisterToCourse;

public interface RegisterToCourseService {
    Boolean save(RegisterToCourse registerToCourse);

    RegisterToCourse update(RegisterToCourse registerToCourse);
}
