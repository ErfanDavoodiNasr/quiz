package ir.quiz.quiz.dto.response;


import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
public class MessageResponse {
    private String message;
    private String time;

    public MessageResponse(String message) {
        this.message = message;
        this.time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
