package ir.quiz.quiz.mapper;

import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.dto.request.PersonRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentRequestMapper extends BaseMapper<Student, PersonRequest> {
}
