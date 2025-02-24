package ir.quiz.quiz.controller;

import ir.quiz.quiz.dto.request.CourseRequest;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.service.CourseService;
import ir.quiz.quiz.service.StudentService;
import ir.quiz.quiz.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid CourseRequest courseRequest) {
        Boolean result = courseService.save(courseRequest);
        return result ? ResponseEntity.ok(new MessageResponse("course saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }


    @GetMapping("/find_all_courses")
    public ResponseEntity<?> findAll() {
        Optional<List<Course>> result = courseService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("no course found"));
        }
        return ResponseEntity.ok(result.get());
    }


    @GetMapping("/find_course_by_id")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        Optional<Course> result = courseService.findById(id);
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("no course found"));
        }
        return ResponseEntity.ok(result.get());
    }


    @PutMapping("/add_teacher_to_course")
    public ResponseEntity<?> addTeacherToCourse(@RequestParam Long teacherId, @RequestParam Long courseId) {
        Optional<Course> course = courseService.findById(courseId);
        if (course.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("no course found"));
        }
        course.get().setTeacher(teacherService.findReferenceById(teacherId).get());
        courseService.update(course.get());
        return ResponseEntity.ok(new MessageResponse("save course to teacher was successful"));
    }

    @DeleteMapping("/remove_teacher_from_course")
    public ResponseEntity<?> removeTeacherFromCourse(@RequestParam Long courseId) {
        Optional<Course> course = courseService.findById(courseId);
        if (course.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("no course found"));
        }
        course.get().setTeacher(null);
        courseService.update(course.get());
        return ResponseEntity.ok(new MessageResponse("remove course from teacher was successful"));
    }

    @PutMapping("/add_student_to_course")
    public ResponseEntity<?> addStudentToCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        Optional<Student> student = studentService.findById(studentId);
        if (student.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("no student found"));
        }
        Optional<Course> course = courseService.findReferenceById(courseId);
        student.get().getCourses().add(course.get());
        courseService.update(course.get());
        return ResponseEntity.ok(new MessageResponse("save course to student was successful"));
    }


    @DeleteMapping("/remove_student_from_course")
    public ResponseEntity<?> removeStudentFromCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        Optional<Course> course = courseService.findById(courseId);
        if (course.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("no course found"));
        }
        course.get().getStudents().remove(studentService.findReferenceById(studentId).get());
        courseService.update(course.get());
        return ResponseEntity.ok(new MessageResponse("remove course from student was successful"));
    }
}