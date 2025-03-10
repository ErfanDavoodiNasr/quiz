package ir.quiz.quiz.controller;

import ir.quiz.quiz.dto.request.MultipleChoiceQuestionRequest;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.dto.response.MultipleChoiceQuestionResponse;
import ir.quiz.quiz.service.MultipleChoiceQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api/multiple-choice-questions")
@RequiredArgsConstructor
public class MultipleChoiceQuestionController {

    private final MultipleChoiceQuestionService multipleChoiceQuestionService;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid MultipleChoiceQuestionRequest multipleChoiceQuestionRequest) {
        MultipleChoiceQuestionResponse result = multipleChoiceQuestionService.save(multipleChoiceQuestionRequest);
        return result != null ? ResponseEntity.ok(new MessageResponse("question saves successfully")) : ResponseEntity.status(500).body("there is some problem please try again");
    }


    @DeleteMapping
    public ResponseEntity<?> remove(@RequestParam("id") Long id) {
        Boolean result = multipleChoiceQuestionService.remove(id);
        return result ? ResponseEntity.ok(new MessageResponse("question removed successfully")) : ResponseEntity.status(500).body("there is some problem please try again");
    }

    @GetMapping
    public ResponseEntity<?> findAllMultipleChoiceQuestion() {
        Optional<List<MultipleChoiceQuestionResponse>> result = multipleChoiceQuestionService.findAll();
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.status(404).body(new MessageResponse("no question found"));
    }
}
