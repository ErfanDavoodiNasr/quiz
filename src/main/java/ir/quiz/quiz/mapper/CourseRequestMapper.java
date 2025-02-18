package ir.quiz.quiz.mapper;

import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.dto.request.CourseRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseRequestMapper extends BaseMapper<Course, CourseRequest> {
}
