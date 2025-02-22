package ir.quiz.quiz.mapper;

import ir.quiz.quiz.dto.response.OwnerResponse;
import ir.quiz.quiz.model.Owner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerResponseMapper extends BaseMapper<Owner, OwnerResponse> {
}
