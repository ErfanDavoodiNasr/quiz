package ir.quiz.quiz.dto.response;


import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class StudentCertificate {
    private Long id;
    private StudentResponse studentResponse;
    private Double studentScore;
    private Double totalScore;
}
