package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.exception.OwnerNotFoundException;
import ir.quiz.quiz.exception.StudentNotFoundException;
import ir.quiz.quiz.model.Status;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.dto.PersonRequest;
import ir.quiz.quiz.model.dto.response.StudentResponse;
import ir.quiz.quiz.model.dto.search.StudentSearch;
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

    private static Optional<StudentResponse> studentToStudentResponse(Optional<Student> studentOptional) {
        return Optional.ofNullable(StudentResponse.builder()
                .firstName(studentOptional.get().getFirstName())
                .lastName(studentOptional.get().getLastName())
                .username(studentOptional.get().getUsername())
                .courses(studentOptional.get().getCourses())
                .status(studentOptional.get().getStatus())
                .build());
    }

    private Student personRequestToStudent(PersonRequest personRequest) {
        return Student.builder()
                .firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .password(bCryptPasswordEncoder.encode(personRequest.getPassword()))
                .username(personRequest.getUsername())
                .status(Status.AWAITING_CONFIRMATION)
                .build();
    }

    @Override
    public Optional<StudentResponse> findByUsernameAndPassword(String username, String password) {
        Optional<Student> studentOptional = studentRepository.findByUsername(username);
        if (studentOptional.isEmpty()) {
            throw new StudentNotFoundException("student not found");
        }
        if (bCryptPasswordEncoder.matches(password, studentOptional.get().getPassword())) {
            return studentToStudentResponse(studentOptional);
        } else {
            throw new OwnerNotFoundException("your username or password is wrong");
        }
    }

    @Override
    public List<Student> findAll(StudentSearch search) {
        return studentRepository.findAll(
                (root, query, cb) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    fillFirstNamePredicates(predicates, root, cb, search.getFirstName());
                    fillLastNamePredicates(predicates, root, cb, search.getLastName());
                    fillUsernamePredicates(predicates, root, cb, search.getUsername());
                    return predicates.isEmpty() ? null : cb.and(predicates.toArray(new Predicate[0]));
                }
        );
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
                    root.get("lastname"),
                    "%" + lastName + "%"
            ));
        }

    }

    private void fillFirstNamePredicates(List<Predicate> predicates, Root<Student> root, CriteriaBuilder cb, String firstName) {
        if (StringUtils.isNoneBlank(firstName)) {
            predicates.add(cb.like(
                    root.get("firstname"),
                    "%" + firstName + "%"
            ));
        }

    }

    @Override
    public Boolean save(PersonRequest studentRequest) {
        Student student = personRequestToStudent(studentRequest);
        Student result = studentRepository.save(student);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Optional<List<Student>> findAllByStatusIsLike(Status status) {
        return studentRepository.findAllByStatusIsLike(status);
    }

    @Override
    public Student update(Student student) {
        if (student == null | student.getId() == null) {
            throw new NullPointerException("student can't be null");
        }
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
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
