package ir.quiz.quiz.mapper;


import ir.quiz.quiz.dto.response.QuizResponse;
import ir.quiz.quiz.model.quiz.Quiz;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizResponseMapper extends BaseMapper<Quiz, QuizResponse> {
}
