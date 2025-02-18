package ir.quiz.quiz.service;

import ir.quiz.quiz.dto.request.OwnerUpdateRequest;
import ir.quiz.quiz.dto.response.OwnerResponse;
import ir.quiz.quiz.model.Owner;

import java.util.Optional;

public interface OwnerService {

    Boolean save(Owner owner);

    void remove(Long id);

    Owner update(OwnerUpdateRequest ownerUpdateRequest);

    Optional<OwnerResponse> findByUsernameAndPassword(String username, String password);
}