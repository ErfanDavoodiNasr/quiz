package ir.quiz.quiz.mapper;


import ir.quiz.quiz.dto.response.MultipleQuizQuestionResponsePage;
import ir.quiz.quiz.model.quiz.MultipleChoiceQuestion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MultipleQuizQuestionResponsePageMapper extends BaseMapper<MultipleChoiceQuestion, MultipleQuizQuestionResponsePage> {
}
