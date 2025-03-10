package ir.quiz.quiz.dto.response;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@SuperBuilder
@Data
public class MessageResponse {
    private String message;
    private String time;

    public MessageResponse(String message) {
        this.message = message;
        this.time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
