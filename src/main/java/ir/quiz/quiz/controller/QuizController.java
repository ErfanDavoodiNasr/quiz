package ir.quiz.quiz.controller;


import ir.quiz.quiz.dto.request.*;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.model.quiz.Quiz;
import ir.quiz.quiz.model.quiz.QuestionType;
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

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid QuizUpdateRequest quizRequest) {
        return ResponseEntity.ok(quizService.update(quizRequest));
    }

    @DeleteMapping
    public ResponseEntity<?> remove(@RequestParam("quizId") Long quizId) {
        Boolean result = quizService.remove(quizId);
        return result ? ResponseEntity.ok(new MessageResponse("quiz removed successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        Optional<List<Quiz>> result = quizService.findAll();
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.status(404).body(new MessageResponse("no quiz found"));
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam("id") Long id) {
        Optional<Quiz> quiz = quizService.findById(id);
        return quiz.isPresent() ? ResponseEntity.ok(quiz.get()) : ResponseEntity.status(404).body(new MessageResponse("no quiz found"));
    }

    @GetMapping("/quizzes_by_teacherId_and_courseId")
    public ResponseEntity<?> quizzesByTeacherIdAndCourseId(@RequestParam("courseId") Long courseId, @RequestParam("teacherId") Long teacherId) {
        Optional<List<Quiz>> result = quizService.findAllByCourseIdAndTeacherId(courseId, teacherId);
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.status(404).body(new MessageResponse("no quiz found"));
    }


    @PostMapping("/add-ready-question-to-quiz")
    public ResponseEntity<?> addReadyQuestionToQuiz(
            @RequestParam(value = "questionId",required = true) Long questionId,
            @RequestParam(value = "quizId",required = true) Long quizId,
            @RequestParam(value = "questionType",required = true) QuestionType questionType,
            @RequestParam(value = "score",required = true) Double score
            ){
        Boolean result = quizService.addReadyQuestionToQuiz(questionId, quizId, score, questionType);
        return result ? ResponseEntity.ok(new MessageResponse("question saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

    @PostMapping("/add-new-multiple-question-to-quiz")
    public ResponseEntity<?> addNewMultipleQuestionToQuiz(
            @RequestBody @Valid MultipleChoiceQuestionRequest multipleChoiceQuestionRequest,
            @RequestParam(value = "quizId",required = true) Long quizId,
            @RequestParam(value = "questionType",required = true) QuestionType questionType,
            @RequestParam(value = "score",required = true) Double score
    ){
        Boolean result = quizService.addNewMultipleQuestionToQuiz(multipleChoiceQuestionRequest, quizId, score, questionType);
        return result ? ResponseEntity.ok(new MessageResponse("question saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

    @PostMapping("/add-new-annotation-question-to-quiz")
    public ResponseEntity<?> addNewAnnotationQuestionToQuiz(
            @RequestBody @Valid AnnotationQuestionRequest annotationQuestionRequest,
            @RequestParam(value = "quizId",required = true) Long quizId,
            @RequestParam(value = "questionType",required = true) QuestionType questionType,
            @RequestParam(value = "score",required = true) Double score
    ){
        Boolean result = quizService.addNewAnnotationQuestionToQuiz(annotationQuestionRequest, quizId, score, questionType);
        return result ? ResponseEntity.ok(new MessageResponse("question saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

}
