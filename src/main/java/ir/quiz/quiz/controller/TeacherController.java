package ir.quiz.quiz.controller;

import ir.quiz.quiz.dto.request.PersonRequest;
import ir.quiz.quiz.dto.request.TeacherUpdateRequest;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.dto.search.TeacherSearch;
import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.service.QuizService;
import ir.quiz.quiz.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final QuizService quizService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid PersonRequest personRequest) {
        Boolean result = teacherService.save(personRequest);
        return result ? ResponseEntity.ok(new MessageResponse("teacher saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid TeacherUpdateRequest teacherUpdateRequest) {
        return ResponseEntity.ok(teacherService.update(teacherUpdateRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestBody TeacherSearch teacherSearch) {
        List<Teacher> teachers = teacherService.findAll(teacherSearch);
        if (teachers.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("teacher not found"));
        }
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/see-student-quiz")
    public ResponseEntity<?> getStudentQuiz(
            @RequestParam("quizId") Long quizId
    ){
        return ResponseEntity.ok(quizService.getStudentQuiz(quizId));
    }

    @PutMapping("/set-score-for-student-answer")
    public ResponseEntity<?> setScoreForStudentAnswer(
            @RequestParam("answerId") Long answerId,
            @RequestParam("questionId") Long questionId,
            @RequestParam("score") Double score
    ){

        Boolean result = quizService.setScoreForQuiz(answerId, questionId, score);
        return result ? ResponseEntity.ok(new MessageResponse("score changed successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

    @GetMapping("/get-student-certificate")
    public ResponseEntity<?> getStudentCertificate(
            @RequestParam("quizId") Long quizId
    ){
        return ResponseEntity.ok(quizService.getStudentCertificate(quizId));
    }
}