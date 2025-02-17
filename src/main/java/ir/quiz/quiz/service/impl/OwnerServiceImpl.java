package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.model.Owner;
import ir.quiz.quiz.model.dto.OwnerUpdateRequest;
import ir.quiz.quiz.repository.OwnerRepository;
import ir.quiz.quiz.security.PasswordHashing;
import ir.quiz.quiz.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PasswordHashing passwordHashing;

    private static Owner ownerUpdateRequestToOwner(OwnerUpdateRequest ownerUpdateRequest) {
        return Owner.builder()
                .id(ownerUpdateRequest.getId())
                .firstName(ownerUpdateRequest.getFirstName())
                .lastName(ownerUpdateRequest.getLastName())
                .username(ownerUpdateRequest.getUsername())
                .password(ownerUpdateRequest.getPassword())
                .build();
    }

    @Override
    public Boolean save(Owner owner) {
        owner.setPassword(passwordHashing.hash(owner.getPassword()));
        Owner result = ownerRepository.save(owner);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Owner update(OwnerUpdateRequest ownerUpdateRequest) {
        if (ownerUpdateRequest == null | ownerUpdateRequest.getId() == null) {
            throw new NullPointerException("owner can't be null");
        }
        Owner owner = ownerUpdateRequestToOwner(ownerUpdateRequest);
        return ownerRepository.save(owner);
    }

    @Override
    public Optional<Owner> findByUsernameAndPassword(String username, String password) {
        return ownerRepository.findByUsernameAndPassword(username, password);
    }
}