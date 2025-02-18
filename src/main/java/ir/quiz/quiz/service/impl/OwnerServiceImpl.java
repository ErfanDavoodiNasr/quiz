package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.exception.OwnerNotFoundException;
import ir.quiz.quiz.model.Owner;
import ir.quiz.quiz.model.dto.OwnerUpdateRequest;
import ir.quiz.quiz.model.dto.response.OwnerResponse;
import ir.quiz.quiz.repository.OwnerRepository;
import ir.quiz.quiz.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static Optional<OwnerResponse> OwnerToOwnerResponse(Optional<Owner> ownerOptional) {
        return Optional.ofNullable(OwnerResponse.builder()
                .username(ownerOptional.get().getUsername())
                .firstName(ownerOptional.get().getFirstName())
                .lastName(ownerOptional.get().getLastName())
                .build());
    }

    private Owner ownerUpdateRequestToOwner(OwnerUpdateRequest ownerUpdateRequest) {
        return Owner.builder()
                .id(ownerUpdateRequest.getId())
                .firstName(ownerUpdateRequest.getFirstName())
                .lastName(ownerUpdateRequest.getLastName())
                .username(ownerUpdateRequest.getUsername())
                .password(bCryptPasswordEncoder.encode(ownerUpdateRequest.getPassword()))
                .build();
    }

    @Override
    public Boolean save(Owner owner) {
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
    public Optional<OwnerResponse> findByUsernameAndPassword(String username, String password) {
        Optional<Owner> ownerOptional = ownerRepository.findByUsername(username);
        if (ownerOptional.isEmpty()) {
            throw new OwnerNotFoundException("owner not found");
        }
        if (bCryptPasswordEncoder.matches(password, ownerOptional.get().getPassword())) {
            return OwnerToOwnerResponse(ownerOptional);
        } else {
            throw new OwnerNotFoundException("your username or password is wrong");
        }
    }
}