package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.config.JwtService;
import ir.quiz.quiz.dto.request.PersonRequest;
import ir.quiz.quiz.dto.request.TeacherUpdateRequest;
import ir.quiz.quiz.dto.response.JwtTokenResponse;
import ir.quiz.quiz.dto.search.TeacherSearch;
import ir.quiz.quiz.exception.TeacherNotFoundException;
import ir.quiz.quiz.mapper.TeacherRequestMapper;
import ir.quiz.quiz.mapper.TeacherUpdateRequestMapper;
import ir.quiz.quiz.model.Role;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.repository.TeacherRepository;
import ir.quiz.quiz.service.TeacherService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherRequestMapper teacherRequestMapper;
    private final TeacherUpdateRequestMapper teacherUpdateRequestMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean save(PersonRequest teacherRequest) {
        Teacher teacher = teacherRequestMapper.convertDtoToEntity(teacherRequest);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacher.setStatus(Status.AWAITING_CONFIRMATION);
        teacher.setRole(Role.TEACHER);
        Teacher result = teacherRepository.save(teacher);
        if (result.getId() != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Teacher update(TeacherUpdateRequest teacherUpdateRequest) {
        if (teacherUpdateRequest == null || teacherUpdateRequest.getId() == null) {
            throw new NullPointerException("teacher can't be null");
        }
        Teacher teacher = teacherUpdateRequestMapper.convertDtoToEntity(teacherUpdateRequest);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateStatus(Long id, Status status) {
        Optional<Teacher> teacher = checkTeacherIsExist(teacherRepository.findById(id));
        teacher.get().setStatus(status);
        return teacherRepository.save(teacher.get());
    }

    private Optional<Teacher> checkTeacherIsExist(Optional<Teacher> teacherRepository) {
        Optional<Teacher> teacherOptional = teacherRepository;
        if (teacherOptional.isEmpty()) {
            throw new TeacherNotFoundException("teacher not found");
        }
        return teacherOptional;
    }

    @Override
    public List<Teacher> findAll(TeacherSearch search) {
        return teacherRepository.findAll(
                (root, query, cb) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    fillFirstNamePredicates(predicates, root, cb, search.getFirstName());
                    fillLastNamePredicates(predicates, root, cb, search.getLastName());
                    fillUsernamePredicates(predicates, root, cb, search.getUsername());
                    fillStatusPredicates(predicates, root, cb, search.getStatus());
                    return predicates.isEmpty() ? null : cb.and(predicates.toArray(new Predicate[0]));
                }
        );
    }

    private void fillStatusPredicates(List<Predicate> predicates, Root<Teacher> root, CriteriaBuilder cb, Status status) {
        if (status != null) {
            predicates.add(cb.equal(
                    root.get("status"),
                    status
            ));
        }
    }

    private void fillUsernamePredicates(List<Predicate> predicates, Root<Teacher> root, CriteriaBuilder cb, String username) {
        if (StringUtils.isNoneBlank(username)) {
            predicates.add(cb.like(
                    root.get("username"),
                    "%" + username + "%"
            ));
        }
    }

    private void fillLastNamePredicates(List<Predicate> predicates, Root<Teacher> root, CriteriaBuilder cb, String lastName) {
        if (StringUtils.isNoneBlank(lastName)) {
            predicates.add(cb.like(
                    root.get("lastName"),
                    "%" + lastName + "%"
            ));
        }
    }

    private void fillFirstNamePredicates(List<Predicate> predicates, Root<Teacher> root, CriteriaBuilder cb, String firstName) {
        if (StringUtils.isNoneBlank(firstName)) {
            predicates.add(cb.like(
                    root.get("firstName"),
                    "%" + firstName + "%"
            ));
        }
    }

}
