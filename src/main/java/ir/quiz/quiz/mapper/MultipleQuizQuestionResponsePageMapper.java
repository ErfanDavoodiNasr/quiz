package ir.quiz.quiz.mapper;


import ir.quiz.quiz.dto.response.MultipleQuizQuestionResponsePage;
import ir.quiz.quiz.model.quiz.QuizQuestion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MultipleQuizQuestionResponsePageMapper extends BaseMapper<QuizQuestion, MultipleQuizQuestionResponsePage> {
}
