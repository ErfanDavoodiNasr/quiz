package ir.quiz.quiz.mapper;

import ir.quiz.quiz.model.Owner;
import ir.quiz.quiz.model.Student;
import ir.quiz.quiz.model.dto.response.OwnerResponse;
import ir.quiz.quiz.model.dto.response.StudentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerResponseMapper extends BaseMapper<Owner, OwnerResponse> {
}
