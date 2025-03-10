package ir.quiz.quiz.mapper;

import ir.quiz.quiz.dto.response.AnnotationQuestionResponse;
import ir.quiz.quiz.model.quiz.AnnotationQuestion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnnotationQuestionResponseMapper extends BaseMapper<AnnotationQuestion, AnnotationQuestionResponse> {
}
