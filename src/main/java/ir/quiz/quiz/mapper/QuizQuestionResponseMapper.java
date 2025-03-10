package ir.quiz.quiz.mapper;


import ir.quiz.quiz.dto.response.QuizQuestionResponse;
import ir.quiz.quiz.model.quiz.QuizQuestion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizQuestionResponseMapper extends BaseMapper<QuizQuestion, QuizQuestionResponse> {
}
