package ir.quiz.quiz.model.dto.search;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentSearch {
    private String firstName;
    private String lastName;
    private String username;
}
