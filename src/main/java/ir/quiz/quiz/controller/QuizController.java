package ir.quiz.quiz.controller;


import ir.quiz.quiz.dto.request.QuizRequest;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.model.Quiz;
import ir.quiz.quiz.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.LongFunction;

@Controller
@RequestMapping("api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid QuizRequest quizRequest) {
        Boolean result = quizService.save(quizRequest);
        return result ? ResponseEntity.ok(new MessageResponse("quiz saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

    @DeleteMapping
    public ResponseEntity<?> remove(@RequestParam("quizId") Long quizId){
        Boolean result = quizService.remove(quizId);
        return result ? ResponseEntity.ok(new MessageResponse("quiz removed successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        Optional<List<Quiz>> result = quizService.findAll();
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.status(404).body(new MessageResponse("no quiz found"));
    }
    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") Long id) {
        Optional<Quiz> quiz = quizService.findById(id);
        return quiz.isPresent() ? ResponseEntity.ok(quiz.get()) : ResponseEntity.status(404).body(new MessageResponse("no quiz found"));
    }

    @GetMapping("/quizzes_by_course_id")
    public ResponseEntity<?> findByCourseId(@RequestParam("id") Long id) {
        Optional<Quiz> quiz = quizService.findById(id);
        return quiz.isPresent() ? ResponseEntity.ok(quiz.get()) : ResponseEntity.status(404).body(new MessageResponse("no quiz found"));
    }

    @GetMapping("/quizzes_by_teacherId_and_courseId")
    public  ResponseEntity<?> quizzesByTeacherIdAndCourseId(@RequestParam("courseId") Long courseId , @RequestParam("teacherId") Long teacherId){
        Optional<List<Quiz>> result = quizService.findAllByCourseIdAndTeacherId(courseId, teacherId);
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.status(404).body(new MessageResponse("no quiz found"));
    }

}
