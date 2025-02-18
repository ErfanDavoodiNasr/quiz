package ir.quiz.quiz.mapper;

import ir.quiz.quiz.dto.request.OwnerUpdateRequest;
import ir.quiz.quiz.model.Owner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerUpdateRequestMapper extends BaseMapper<Owner, OwnerUpdateRequest> {
}
