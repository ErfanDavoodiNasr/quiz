package ir.quiz.quiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OwnerResponse {
    private String username;
    private String password;
}
