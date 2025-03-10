package ir.quiz.quiz.dto.response;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@SuperBuilder
@Data
public class JwtTokenResponse {

    private String token;
    private String time;

    public JwtTokenResponse(String token) {
        this.token = token;
        time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
