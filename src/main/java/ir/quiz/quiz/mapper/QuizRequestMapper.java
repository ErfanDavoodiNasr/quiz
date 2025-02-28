package ir.quiz.quiz.mapper;

import ir.quiz.quiz.dto.request.QuizRequest;
import ir.quiz.quiz.model.quiz.Quiz;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizRequestMapper extends BaseMapper<Quiz, QuizRequest> {
}
