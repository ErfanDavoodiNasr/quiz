package ir.quiz.quiz.mapper;


import ir.quiz.quiz.dto.response.MultipleChoiceQuestionResponse;
import ir.quiz.quiz.model.quiz.MultipleChoiceQuestion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MultipleChoiceQuestionResponseMapper extends BaseMapper<MultipleChoiceQuestion, MultipleChoiceQuestionResponse> {
}
