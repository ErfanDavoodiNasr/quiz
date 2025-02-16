package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.model.Principal;
import ir.quiz.quiz.model.dto.PersonRequest;
import ir.quiz.quiz.model.dto.PrincipalUpdateRequest;
import ir.quiz.quiz.repository.PrincipalRepository;
import ir.quiz.quiz.service.PrincipalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalServiceImpl implements PrincipalService {

    private final PrincipalRepository principalRepository;

    private static Principal personRequestToPrincipal(PersonRequest personRequest) {
        return Principal.builder()
                .firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .phoneNumber(personRequest.getPhoneNumber())
                .nationalCode(personRequest.getNationalCode())
                .build();
    }

    @Override
    public Boolean save(PersonRequest personRequest) {
        Principal principal = personRequestToPrincipal(personRequest);
        Principal result = principalRepository.save(principal);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Principal update(PrincipalUpdateRequest principalUpdateRequest) {
        if (principalUpdateRequest == null | principalUpdateRequest.getId() != null) {
            throw new NullPointerException("principal can't be null");
        }
        Principal principal = principalUpdateRequestToPrincipal(principalUpdateRequest);
        return principalRepository.save(principal);
    }

    private static Principal principalUpdateRequestToPrincipal(PrincipalUpdateRequest principalUpdateRequest) {
        return Principal.builder()
                .id(principalUpdateRequest.getId())
                .firstName(principalUpdateRequest.getFirstName())
                .lastName(principalUpdateRequest.getLastName())
                .nationalCode(principalUpdateRequest.getNationalCode())
                .phoneNumber(principalUpdateRequest.getPhoneNumber())
                .courses(principalUpdateRequest.getCourses())
                .build();
    }
}
