package ir.quiz.quiz.service.impl;


import ir.quiz.quiz.dto.request.CourseRequest;
import ir.quiz.quiz.exception.StudentNotFoundException;
import ir.quiz.quiz.exception.TeacherNotFoundException;
import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.repository.CourseRepository;
import ir.quiz.quiz.repository.StudentRepository;
import ir.quiz.quiz.repository.TeacherRepository;
import ir.quiz.quiz.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static ir.quiz.quiz.util.Help.checkTimeIsValid;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public Boolean save(CourseRequest courseRequest) {
        if (courseRequest == null) {
            throw new NullPointerException("course request can't be null");
        }
        Course mappedCourse = convertDtoToEntity(courseRequest);
        checkTimeIsValid(mappedCourse.getStartAt(), mappedCourse.getEndAt());
        Course result = courseRepository.save(mappedCourse);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }


    private Course convertDtoToEntity(CourseRequest courseRequest) {
        return Course.builder()
                .name(courseRequest.getName())
                .startAt(LocalDateTime.parse(courseRequest.getStartAt(), formatter))
                .endAt(LocalDateTime.parse(courseRequest.getEndAt(), formatter))
                .build();
    }

    @Override
    public Course update(Course course) {
        if (course == null || course.getId() == null) {
            throw new NullPointerException("course can't be null");
        }
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Optional<Course> findReferenceById(Long id) {
        return Optional.ofNullable(courseRepository.getReferenceById(id));
    }

    @Override
    public Optional<List<Course>> findAll() {
        return Optional.ofNullable(courseRepository.findAll());
    }

    @Override
    public Optional<List<Course>> findAllByTeacherId(Number teacherId) {
        if (teacherId != null) {
            courseRepository.findAllByTeacher_Id(teacherId);
        }
        return Optional.empty();
    }

    @Override
    public Boolean removeStudentFromCourse(Long studentId, Long courseId) {
        Optional<Student> student = Optional.ofNullable(studentRepository.getReferenceById(studentId));
        if (student.isEmpty()) {
            throw new StudentNotFoundException("student not found");
        }
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new StudentNotFoundException("course not found");
        }
        course.get().getStudents().remove(student.get());
        return courseRepository.save(course.get()).getStudents().contains(student.get());
    }

    @Override
    public Boolean addStudentToCourse(Long studentId, Long courseId) {
        Optional<Student> student = Optional.ofNullable(studentRepository.getReferenceById(studentId));
        if (student.isEmpty()) {
            throw new StudentNotFoundException("student not found");
        }
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new StudentNotFoundException("course not found");
        }
        course.get().getStudents().add(student.get());
        return courseRepository.save(course.get()).getStudents().contains(student.get());
    }

    @Override
    public Boolean removeTeacherFromCourse(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new StudentNotFoundException("course not found");
        }
        course.get().setTeacher(null);
        return courseRepository.save(course.get()).getTeacher().equals(null);
    }

    @Override
    public Boolean addTeacherToCourse(Long teacherId, Long courseId) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (teacher.isEmpty()) {
            throw new TeacherNotFoundException("teacher not found");
        }
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new StudentNotFoundException("course not found");
        }
        course.get().setTeacher(teacher.get());
        return courseRepository.save(course.get()).getTeacher().equals(teacher.get());
    }
}
