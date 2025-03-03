package ir.quiz.quiz.controller;

import ir.quiz.quiz.dto.request.MultipleChoiceQuestionRequest;
import ir.quiz.quiz.dto.request.QuestionOptionRequest;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.model.quiz.MultipleChoiceQuestion;
import ir.quiz.quiz.service.MultipleChoiceQuestionService;
import ir.quiz.quiz.service.QuestionOptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api/multiple-choice-questions")
@RequiredArgsConstructor
public class MultipleChoiceQuestionController {

    private final MultipleChoiceQuestionService multipleChoiceQuestionService;
    private final QuestionOptionService questionOptionService;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid MultipleChoiceQuestionRequest multipleChoiceQuestionRequest) {
        MultipleChoiceQuestion result = multipleChoiceQuestionService.save(multipleChoiceQuestionRequest);
        return result != null ? ResponseEntity.ok(new MessageResponse("question saves successfully")) : ResponseEntity.status(500).body("there is some problem please try again");
    }

    @PostMapping("/add-option")
    public ResponseEntity<?> addOptionToQuestion(@RequestBody @Valid QuestionOptionRequest questionOptionRequest) {
        Boolean result = multipleChoiceQuestionService.addOptionTOQuestion(questionOptionRequest);
        return result ? ResponseEntity.ok(new MessageResponse("option saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

    @GetMapping
    public ResponseEntity<?> findAllMultipleChoiceQuestion() {
        Optional<List<MultipleChoiceQuestion>> result = multipleChoiceQuestionService.findAll();
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.status(404).body(new MessageResponse("no question found"));
    }
}
