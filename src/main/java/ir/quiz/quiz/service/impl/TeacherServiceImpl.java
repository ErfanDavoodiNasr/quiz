package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.exception.OwnerNotFoundException;
import ir.quiz.quiz.exception.TeacherNotFoundException;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.dto.PersonRequest;
import ir.quiz.quiz.model.dto.response.TeacherResponse;
import ir.quiz.quiz.model.dto.search.TeacherSearch;
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

    private static Optional<TeacherResponse> teacherToTeacherResponse(Optional<Teacher> teacherOptional) {
        return Optional.ofNullable(TeacherResponse.builder()
                .firstName(teacherOptional.get().getFirstName())
                .lastName(teacherOptional.get().getLastName())
                .username(teacherOptional.get().getUsername())
                .courses(teacherOptional.get().getCourses())
                .status(teacherOptional.get().getStatus())
                .build());
    }

    private Teacher personRequestToTeacher(PersonRequest personRequest) {
        return Teacher.builder()
                .firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .username(personRequest.getUsername())
                .password(bCryptPasswordEncoder.encode(personRequest.getPassword()))
                .status(Status.AWAITING_CONFIRMATION)
                .build();
    }

    @Override
    public Boolean save(PersonRequest teacherRequest) {
        Teacher teacher = personRequestToTeacher(teacherRequest);
        Teacher result = teacherRepository.save(teacher);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Optional<List<Teacher>> findAllByStatusIsLike(Status status) {
        return teacherRepository.findAllByStatusIsLike(status);
    }

    @Override
    public Teacher update(Teacher teacher) {
        if (teacher == null || teacher.getId() == null) {
            throw new NullPointerException("teacher can't be null");
        }
        return teacherRepository.save(teacher);
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
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Optional<TeacherResponse> findByUsernameAndPassword(String username, String password) {
        Optional<Teacher> teacherOptional = teacherRepository.findByUsername(username);
        if (teacherOptional.isEmpty()) {
            throw new TeacherNotFoundException("teacher not found");
        }
        if (bCryptPasswordEncoder.matches(password, teacherOptional.get().getPassword())) {
            return teacherToTeacherResponse(teacherOptional);
        } else {
            throw new OwnerNotFoundException("your username or password is wrong");
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
                    return predicates.isEmpty() ? null : cb.and(predicates.toArray(new Predicate[0]));
                }
        );
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
