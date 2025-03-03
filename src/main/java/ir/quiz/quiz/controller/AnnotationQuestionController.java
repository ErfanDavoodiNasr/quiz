package ir.quiz.quiz.controller;


import ir.quiz.quiz.dto.request.AnnotationQuestionRequest;
import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.model.quiz.AnnotationQuestion;
import ir.quiz.quiz.service.AnnotationQuestionService;
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
@RequestMapping("/api/annotation-questions")
@RequiredArgsConstructor
public class AnnotationQuestionController {


    private final AnnotationQuestionService annotationQuestionService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AnnotationQuestionRequest annotationQuestionRequest) {
        Long result = annotationQuestionService.save(annotationQuestionRequest);
        return result != null ? ResponseEntity.ok(new MessageResponse("question saves successfully")) : ResponseEntity.status(500).body("there is some problem please try again");
    }


    @GetMapping
    public ResponseEntity<?> findAllAnnotationQuestion() {
        Optional<List<AnnotationQuestion>> result = annotationQuestionService.findAll();
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.status(404).body(new MessageResponse("no question found"));
    }
}
