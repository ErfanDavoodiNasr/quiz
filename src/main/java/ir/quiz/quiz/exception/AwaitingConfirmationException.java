package ir.quiz.quiz.exception;

public class AwaitingConfirmationException extends RuntimeException {
    public AwaitingConfirmationException(String message) {
        super(message);
    }
}
