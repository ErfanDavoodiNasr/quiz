package ir.quiz.quiz.mapper;

import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.dto.response.TeacherResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherResponseMapper extends BaseMapper<Teacher, TeacherResponse> {
}
