package ir.quiz.quiz.mapper;

import ir.quiz.quiz.dto.response.QuestionOptionResponse;
import ir.quiz.quiz.model.quiz.QuestionOption;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionOptionResponseMapper extends BaseMapper<QuestionOption, QuestionOptionResponse> {
}
