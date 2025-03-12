package ir.quiz.quiz.mapper;


import ir.quiz.quiz.dto.response.QuizQuestionAnswerResponse;
import ir.quiz.quiz.model.quiz.QuizQuestionAnswer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizQuestionAnswerResponseMapper extends BaseMapper<QuizQuestionAnswer, QuizQuestionAnswerResponse>{
}
