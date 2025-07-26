package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.dto.request.PersonRequest;
import ir.quiz.quiz.dto.request.StudentUpdateRequest;
import ir.quiz.quiz.dto.response.StudentResponse;
import ir.quiz.quiz.dto.search.StudentSearch;
import ir.quiz.quiz.exception.StudentNotFoundException;
import ir.quiz.quiz.mapper.StudentRequestMapper;
import ir.quiz.quiz.mapper.StudentResponseMapper;
import ir.quiz.quiz.mapper.StudentUpdateRequestMapper;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.repository.StudentRepository;
import ir.quiz.quiz.service.StudentService;
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
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StudentResponseMapper studentResponseMapper;
    private final StudentRequestMapper studentRequestMapper;
    private final StudentUpdateRequestMapper studentUpdateRequestMapper;

    @Override
    public Optional<StudentResponse> findByUsernameAndPassword(String username, String password) {
        Optional<Student> studentOptional = studentRepository.findByUsername(username);
        if (studentOptional.isEmpty()) {
            throw new StudentNotFoundException("student not found");
        }
        if (bCryptPasswordEncoder.matches(password, studentOptional.get().getPassword())) {
            return Optional.ofNullable(studentResponseMapper.convertEntityToDto(studentOptional.get()));
        } else {
            throw new StudentNotFoundException("your username or password is wrong");
        }
    }

    @Override
    public Optional<Student> findReferenceById(Long id) {
        return Optional.ofNullable(studentRepository.getReferenceById(id));
    }

    @Override
    public List<Student> findAll(StudentSearch search) {
        return studentRepository.findAll(
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

    private void fillStatusPredicates(List<Predicate> predicates, Root<Student> root, CriteriaBuilder cb, Status status) {
        if (status != null) {
            predicates.add(cb.equal(
                    root.get("status"),
                    status
            ));
        }
    }

    private void fillUsernamePredicates(List<Predicate> predicates, Root<Student> root, CriteriaBuilder cb, String username) {
        if (StringUtils.isNoneBlank(username)) {
            predicates.add(cb.like(
                    root.get("username"),
                    "%" + username + "%"
            ));
        }
    }

    private void fillLastNamePredicates(List<Predicate> predicates, Root<Student> root, CriteriaBuilder cb, String lastName) {
        if (StringUtils.isNoneBlank(lastName)) {
            predicates.add(cb.like(
                    root.get("lastName"),
                    "%" + lastName + "%"
            ));
        }

    }

    private void fillFirstNamePredicates(List<Predicate> predicates, Root<Student> root, CriteriaBuilder cb, String firstName) {
        if (StringUtils.isNoneBlank(firstName)) {
            predicates.add(cb.like(
                    root.get("firstName"),
                    "%" + firstName + "%"
            ));
        }

    }

    @Override
    public Boolean save(PersonRequest studentRequest) {
        studentRequest.setPassword(bCryptPasswordEncoder.encode(studentRequest.getPassword()));
        Student student = studentRequestMapper.convertDtoToEntity(studentRequest);
        student.setStatus(Status.AWAITING_CONFIRMATION);
        Student result = studentRepository.save(student);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Student update(StudentUpdateRequest studentUpdateRequest) {
        if (studentUpdateRequest == null | studentUpdateRequest.getId() == null) {
            throw new NullPointerException("student can't be null");
        }
        studentUpdateRequest.setPassword(bCryptPasswordEncoder.encode(studentUpdateRequest.getPassword()));
        return studentRepository.save(studentUpdateRequestMapper.convertDtoToEntity(studentUpdateRequest));
    }

    @Override
    public Student updateStatus(Long id, Status status) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new StudentNotFoundException("student not found");
        }
        student.get().setStatus(status);
        return studentRepository.save(student.get());
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }
}
