package ir.quiz.quiz.mapper;

import ir.quiz.quiz.model.Teacher;
import ir.quiz.quiz.model.dto.request.PersonRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherRequestMapper extends BaseMapper<Teacher, PersonRequest> {
}
