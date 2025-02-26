package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.dto.request.PersonRequest;
import ir.quiz.quiz.dto.request.TeacherUpdateRequest;
import ir.quiz.quiz.dto.response.TeacherResponse;
import ir.quiz.quiz.dto.search.TeacherSearch;
import ir.quiz.quiz.exception.TeacherNotFoundException;
import ir.quiz.quiz.mapper.TeacherRequestMapper;
import ir.quiz.quiz.mapper.TeacherResponseMapper;
import ir.quiz.quiz.mapper.TeacherUpdateRequestMapper;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.repository.CourseRepository;
import ir.quiz.quiz.repository.TeacherRepository;
import ir.quiz.quiz.service.TeacherService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TeacherResponseMapper teacherResponseMapper;
    private final TeacherRequestMapper teacherRequestMapper;
    private final TeacherUpdateRequestMapper teacherUpdateRequestMapper;
    private final CourseRepository courseRepository;

    @Override
    public Boolean save(PersonRequest teacherRequest) {
        teacherRequest.setPassword(bCryptPasswordEncoder.encode(teacherRequest.getPassword()));
        Teacher teacher = teacherRequestMapper.convertDtoToEntity(teacherRequest);
        teacher.setStatus(Status.AWAITING_CONFIRMATION);
        Teacher result = teacherRepository.save(teacher);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Teacher update(TeacherUpdateRequest teacherUpdateRequest) {
        if (teacherUpdateRequest == null || teacherUpdateRequest.getId() == null) {
            throw new NullPointerException("teacher can't be null");
        }
        teacherUpdateRequest.setPassword(bCryptPasswordEncoder.encode(teacherUpdateRequest.getPassword()));
        return teacherRepository.save(teacherUpdateRequestMapper.convertDtoToEntity(teacherUpdateRequest));
    }

    @Override
    public Teacher updateStatus(Long id, Status status) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) {
            throw new TeacherNotFoundException("teacher not found");
        }
        teacher.get().setStatus(status);
        return teacherRepository.save(teacher.get());
    }

    @Override
    public Optional<TeacherResponse> findByUsernameAndPassword(String username, String password) {
        Optional<Teacher> teacherOptional = teacherRepository.findByUsername(username);
        if (teacherOptional.isEmpty()) {
            throw new TeacherNotFoundException("teacher not found");
        }
        if (bCryptPasswordEncoder.matches(password, teacherOptional.get().getPassword())) {
            return Optional.ofNullable(teacherResponseMapper.convertEntityToDto(teacherOptional.get()));
        } else {
            throw new TeacherNotFoundException("your username or password is wrong");
        }
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
