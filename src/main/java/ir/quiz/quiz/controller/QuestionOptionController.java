package ir.quiz.quiz.controller;


import ir.quiz.quiz.dto.request.QuestionOptionRequest;
import ir.quiz.quiz.dto.request.QuestionOptionUpdateRequest;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.dto.response.QuestionOptionResponse;
import ir.quiz.quiz.service.MultipleChoiceQuestionService;
import ir.quiz.quiz.service.QuestionOptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/options")
@RequiredArgsConstructor
public class QuestionOptionController {

    private final MultipleChoiceQuestionService multipleChoiceQuestionService;
    private final QuestionOptionService questionOptionService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid QuestionOptionRequest req) {
        Boolean result = questionOptionService.save(req);
        return result ? ResponseEntity.ok(new MessageResponse("option saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid QuestionOptionUpdateRequest req) {
        QuestionOptionResponse result = questionOptionService.update(req);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<?> remove(@RequestParam("id") Long id) {
        Boolean result = questionOptionService.remove(id);
        return result ? ResponseEntity.ok(new MessageResponse("option removed successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }


    @PostMapping("/add-option-question")
    public ResponseEntity<?> addOptionToQuestion(@RequestBody @Valid QuestionOptionRequest questionOptionRequest) {
        Boolean result = multipleChoiceQuestionService.addOptionTOQuestion(questionOptionRequest);
        return result ? ResponseEntity.ok(new MessageResponse("option saved successfully")) : ResponseEntity.status(500).body(new MessageResponse("there is some problem please try again later"));
    }

}
