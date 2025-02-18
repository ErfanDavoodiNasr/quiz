package ir.quiz.quiz.mapper;

import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.dto.response.StudentResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentResponseMapper extends BaseMapper<Student, StudentResponse> {
}
