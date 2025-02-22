package ir.quiz.quiz.controller;


import ir.quiz.quiz.dto.response.MessageResponse;
import ir.quiz.quiz.exception.OwnerNotFoundException;
import ir.quiz.quiz.exception.StudentNotFoundException;
import ir.quiz.quiz.exception.TeacherNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<MessageResponse> nullPointerException(NullPointerException e) {
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }

    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<MessageResponse> studentNotFoundException(StudentNotFoundException e) {
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }

    @ExceptionHandler(value = TeacherNotFoundException.class)
    public ResponseEntity<MessageResponse> teacherNotFoundException(TeacherNotFoundException e) {
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }

    @ExceptionHandler(value = OwnerNotFoundException.class)
    public ResponseEntity<MessageResponse> ownerNotFoundException(OwnerNotFoundException e) {
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.add(error.getField() + " " + error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(new MessageResponse(errors.toString()));
    }
}
