package ir.quiz.quiz.service;

import ir.quiz.quiz.model.Principal;
import ir.quiz.quiz.model.dto.PersonRequest;
import ir.quiz.quiz.model.dto.PrincipalUpdateRequest;

public interface PrincipalService {
    Boolean save(PersonRequest principalRequest);

    Principal update(PrincipalUpdateRequest principalUpdateRequest);
}
