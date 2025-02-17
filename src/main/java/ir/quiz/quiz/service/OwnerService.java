package ir.quiz.quiz.service;

import ir.quiz.quiz.model.Owner;
import ir.quiz.quiz.model.dto.OwnerUpdateRequest;

import java.util.Optional;

public interface OwnerService {

    Boolean save(Owner owner);

    Owner update(OwnerUpdateRequest ownerUpdateRequest);

    Optional<Owner> findByUsernameAndPassword(String username, String password);
}