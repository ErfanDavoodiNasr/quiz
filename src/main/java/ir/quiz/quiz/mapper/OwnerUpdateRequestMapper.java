package ir.quiz.quiz.mapper;

import ir.quiz.quiz.model.Owner;
import ir.quiz.quiz.model.dto.request.OwnerUpdateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerUpdateRequestMapper extends BaseMapper<Owner, OwnerUpdateRequest> {
}
