package ir.quiz.quiz.mapper;

import ir.quiz.quiz.dto.response.QuestionResponse;
import ir.quiz.quiz.model.quiz.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionResponseMapper extends BaseMapper<Question, QuestionResponse> {
}
