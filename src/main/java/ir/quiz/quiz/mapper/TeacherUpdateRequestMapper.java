package ir.quiz.quiz.mapper;


import ir.quiz.quiz.dto.request.TeacherUpdateRequest;
import ir.quiz.quiz.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherUpdateRequestMapper extends BaseMapper<Teacher, TeacherUpdateRequest> {
}
