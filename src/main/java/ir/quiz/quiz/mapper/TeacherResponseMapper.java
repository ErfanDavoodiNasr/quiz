package ir.quiz.quiz.mapper;

import ir.quiz.quiz.dto.response.TeacherResponse;
import ir.quiz.quiz.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherResponseMapper extends BaseMapper<Teacher, TeacherResponse> {
}
