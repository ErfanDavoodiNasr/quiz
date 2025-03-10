package ir.quiz.quiz.mapper;

import ir.quiz.quiz.dto.request.QuestionOptionRequest;
import ir.quiz.quiz.model.quiz.QuestionOption;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionOptionRequestMapper extends BaseMapper<QuestionOption, QuestionOptionRequest> {
}
