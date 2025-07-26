package ir.quiz.quiz.mapper;

import ir.quiz.quiz.dto.request.StudentUpdateRequest;
import ir.quiz.quiz.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentUpdateRequestMapper extends BaseMapper<Student, StudentUpdateRequest> {
}
