package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.dto.request.OwnerUpdateRequest;
import ir.quiz.quiz.dto.response.OwnerResponse;
import ir.quiz.quiz.exception.OwnerNotFoundException;
import ir.quiz.quiz.mapper.OwnerResponseMapper;
import ir.quiz.quiz.mapper.OwnerUpdateRequestMapper;
import ir.quiz.quiz.model.Owner;
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
    private final OwnerResponseMapper ownerResponseMapper;
    private final OwnerUpdateRequestMapper ownerUpdateRequestMapper;

    @Override
    public Boolean save(Owner owner) {
        owner.setPassword(bCryptPasswordEncoder.encode(owner.getPassword()));
        Owner result = ownerRepository.save(owner);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Owner update(OwnerUpdateRequest ownerUpdateRequest) {
        if (ownerUpdateRequest == null | ownerUpdateRequest.getId() == null) {
            throw new NullPointerException("owner can't be null");
        }
        ownerUpdateRequest.setPassword(bCryptPasswordEncoder.encode(ownerUpdateRequest.getPassword()));
        return ownerRepository.save(ownerUpdateRequestMapper.convertDtoToEntity(ownerUpdateRequest));
    }

    @Override
    public Optional<OwnerResponse> findByUsernameAndPassword(String username, String password) {
        Optional<Owner> ownerOptional = ownerRepository.findByUsername(username);
        if (ownerOptional.isEmpty()) {
            throw new OwnerNotFoundException("owner not found");
        }
        if (bCryptPasswordEncoder.matches(password, ownerOptional.get().getPassword())) {
            return Optional.ofNullable(ownerResponseMapper.convertEntityToDto(ownerOptional.get()));
        } else {
            throw new OwnerNotFoundException("your username or password is wrong");
        }
    }
}