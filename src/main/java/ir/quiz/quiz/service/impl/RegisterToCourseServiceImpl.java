package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.model.RegisterToCourse;
import ir.quiz.quiz.repository.RegisterRepository;
import ir.quiz.quiz.service.RegisterToCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterToCourseServiceImpl implements RegisterToCourseService {

    private final RegisterRepository registerRepository;

    @Override
    public Boolean save(RegisterToCourse registerToCourse) {
        if (registerToCourse == null) {
            throw new NullPointerException("register can't be null");
        }
        return registerRepository.save(registerToCourse).getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public RegisterToCourse update(RegisterToCourse registerToCourse) {
        if (registerToCourse == null || registerToCourse.getId() == null) {
            throw new NullPointerException("register can't be null");
        }
        return registerRepository.save(registerToCourse);

    }
}
