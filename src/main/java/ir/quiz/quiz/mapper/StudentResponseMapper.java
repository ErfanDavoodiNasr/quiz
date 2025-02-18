package ir.quiz.quiz.mapper;

import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.dto.response.StudentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentResponseMapper extends BaseMapper<Student, StudentResponse> {
}
