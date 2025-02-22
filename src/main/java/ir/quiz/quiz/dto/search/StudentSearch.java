package ir.quiz.quiz.dto.search;


import ir.quiz.quiz.model.Status;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentSearch {
    private String firstName;
    private String lastName;
    private String username;
    private Status status;
}
