package ir.quiz.quiz.mapper;

import ir.quiz.quiz.dto.request.PersonRequest;
import ir.quiz.quiz.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherRequestMapper extends BaseMapper<Teacher, PersonRequest> {
}
