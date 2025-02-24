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

    @GetMapping
    public ResponseEntity<?> findAll() {
        Optional<List<Quiz>> result = quizService.findAll();
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.status(404).body(new MessageResponse("no quiz found"));
    }

    @GetMapping
    public ResponseEntity<?> findByCurseId(@RequestParam("courseId") Long courseId) {
        Optional<Quiz> quiz = quizService.findByCourseId(courseId);
        return quiz.isPresent() ? ResponseEntity.ok(quiz.get()) : ResponseEntity.status(404).body(new MessageResponse("no quiz found"));
    }

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") Long id) {
        Optional<Quiz> quiz = quizService.findById(id);
        return quiz.isPresent() ? ResponseEntity.ok(quiz.get()) : ResponseEntity.status(404).body(new MessageResponse("no quiz found"));
    }


}
