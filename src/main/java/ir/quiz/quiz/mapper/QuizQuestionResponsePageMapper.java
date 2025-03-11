package ir.quiz.quiz.mapper;


import ir.quiz.quiz.dto.response.QuizQuestionResponsePage;
import ir.quiz.quiz.model.quiz.QuizQuestion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizQuestionResponsePageMapper extends BaseMapper<QuizQuestion, QuizQuestionResponsePage> {
}
