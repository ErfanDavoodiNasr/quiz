package ir.quiz.quiz.controller;

import ir.quiz.quiz.dto.request.CourseRequest;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.Quiz;
import ir.quiz.quiz.service.CourseService;
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

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid CourseRequest courseRequest) {
        Boolean result = courseService.save(courseRequest);
        return result ? ResponseEntity.ok(new MessageResponse("course saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }


    @GetMapping
    public ResponseEntity<?> findAll() {
        Optional<List<Course>> result = courseService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("no course found"));
        }
        return ResponseEntity.ok(result.get());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Course> result = courseService.findById(id);
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("no course found"));
        }
        return ResponseEntity.ok(result.get());
    }

    @PutMapping("/add_teacher_to_course")
    public ResponseEntity<?> addTeacherToCourse(@RequestParam Long teacherId, @RequestParam Long courseId) {
        Boolean result = courseService.addTeacherToCourse(teacherId, courseId);
        return result ? ResponseEntity.ok(new MessageResponse("remove teacher from course was successful")) : ResponseEntity.badRequest().body(new MessageResponse("there is some problem please try again"));
    }

    @DeleteMapping("/remove_teacher_from_course")
    public ResponseEntity<?> removeTeacherFromCourse(@RequestParam Long courseId) {
        Boolean result = courseService.removeTeacherFromCourse(courseId);
        return result ? ResponseEntity.ok(new MessageResponse("remove teacher from course was successful")) : ResponseEntity.badRequest().body(new MessageResponse("there is some problem please try again"));
    }

    @PutMapping("/add_student_to_course")
    public ResponseEntity<?> addStudentToCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        Boolean result = courseService.addStudentToCourse(studentId, courseId);
        return result ? ResponseEntity.ok(new MessageResponse("add student to course was successful")) : ResponseEntity.badRequest().body(new MessageResponse("there is some problem please try again"));
    }


    @DeleteMapping("/remove_student_from_course")
    public ResponseEntity<?> removeStudentFromCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        Boolean result = courseService.removeStudentFromCourse(studentId, courseId);
        return result ? ResponseEntity.ok(new MessageResponse("remove student from course was successful")) : ResponseEntity.badRequest().body(new MessageResponse("there is some problem please try again"));
    }

    @GetMapping("/find_all_course_quizzes")
    public ResponseEntity<?> findAllCourseQuizzes(@RequestParam("courseId") Long courseId) {
        Optional<List<Quiz>> courseQuizzes = courseService.findAllQuizzesByCourseId(courseId);
        if (courseQuizzes.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("no quiz found"));
        }
        return ResponseEntity.ok(courseQuizzes.get());
    }
}