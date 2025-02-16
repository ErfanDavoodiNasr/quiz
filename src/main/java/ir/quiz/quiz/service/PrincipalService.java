package ir.quiz.quiz.service;

import ir.quiz.quiz.model.Principal;
import ir.quiz.quiz.model.dto.PersonRequest;

public interface PrincipalService {
    Boolean save(PersonRequest principalRequest);

    Principal update(Principal principal);
}
