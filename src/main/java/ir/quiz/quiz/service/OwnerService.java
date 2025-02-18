package ir.quiz.quiz.service;

import ir.quiz.quiz.model.Owner;
import ir.quiz.quiz.model.dto.request.OwnerUpdateRequest;
import ir.quiz.quiz.model.dto.response.OwnerResponse;

import java.util.Optional;

public interface OwnerService {

    Boolean save(Owner owner);

    Owner update(OwnerUpdateRequest ownerUpdateRequest);

    Optional<OwnerResponse> findByUsernameAndPassword(String username, String password);
}