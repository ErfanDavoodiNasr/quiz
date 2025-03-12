package ir.quiz.quiz.controller;


import ir.quiz.quiz.dto.request.AnnotationQuestionRequest;
import ir.quiz.quiz.dto.request.MultipleChoiceQuestionRequest;
import ir.quiz.quiz.dto.request.QuizRequest;
import ir.quiz.quiz.dto.request.QuizUpdateRequest;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.model.quiz.QuestionType;
import ir.quiz.quiz.model.quiz.Quiz;
import ir.quiz.quiz.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Pageable;
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


    @PostMapping("")
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
            @RequestParam(value = "questionId", required = true) Long questionId,
            @RequestParam(value = "quizId", required = true) Long quizId,
            @RequestParam(value = "questionType", required = true) QuestionType questionType,
            @RequestParam(value = "score", required = true) Double score
    ) {
        Boolean result = quizService.addReadyQuestionToQuiz(questionId, quizId, score, questionType);
        return result ? ResponseEntity.ok(new MessageResponse("question saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

    @DeleteMapping("remove-question-from-quiz")
    public ResponseEntity<?> removeQuestionFromQuiz(
            @RequestParam("questionId") Long questionId
    ){
        return null;
        // todo
    }

    @PostMapping("/add-new-multiple-question-to-quiz")
    public ResponseEntity<?> addNewMultipleQuestionToQuiz(
            @RequestBody @Valid MultipleChoiceQuestionRequest multipleChoiceQuestionRequest,
            @RequestParam(value = "quizId", required = true) Long quizId,
            @RequestParam(value = "score", required = true) Double score
    ) {
        Boolean result = quizService.addNewMultipleQuestionToQuiz(multipleChoiceQuestionRequest, quizId, score);
        return result ? ResponseEntity.ok(new MessageResponse("question saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

    @PostMapping("/add-new-annotation-question-to-quiz")
    public ResponseEntity<?> addNewAnnotationQuestionToQuiz(
            @RequestBody @Valid AnnotationQuestionRequest annotationQuestionRequest,
            @RequestParam(value = "quizId", required = true) Long quizId,
            @RequestParam(value = "score", required = true) Double score
    ) {
        Boolean result = quizService.addNewAnnotationQuestionToQuiz(annotationQuestionRequest, quizId, score);
        return result ? ResponseEntity.ok(new MessageResponse("question saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }


    @GetMapping("/see-questions")
    public ResponseEntity<?> seeQuestions(
            @RequestParam("studentId") Long studentId,
            @RequestParam("quizId") Long quizId,
            @RequestParam(value = "index" ,required = false) Integer index) {
        if (index == null){
            return ResponseEntity.ok(quizService.seeQuizQuestion(studentId,quizId,0));
        }else{
            return ResponseEntity.ok(quizService.seeQuizQuestion(studentId,quizId,index));
        }
    }

    @PostMapping("/answer-question")
    public ResponseEntity<?> answerQuestion(
            @RequestParam("studentId") Long studentId,
            @RequestParam("quizQuestionId") Long quizQuestionId,
            @RequestParam("quizId") Long quizId,
            @RequestParam("answer") String answer
    ){
        Boolean result = quizService.answerQuestion(studentId, quizQuestionId, quizId, answer);
        return result ? ResponseEntity.ok(new MessageResponse("answer saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }


    @PostMapping("/submit-quiz")
    public ResponseEntity<?> submitQuiz(
            @RequestParam("quizId") Long quizId,
            @RequestParam("studentId") Long studentId
    ) {
        Boolean result = quizService.submitQuiz(quizId, studentId);
        return result ? ResponseEntity.ok(new MessageResponse("you quiz submitted successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

}
