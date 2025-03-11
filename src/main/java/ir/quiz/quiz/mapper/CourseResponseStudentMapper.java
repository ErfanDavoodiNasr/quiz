package ir.quiz.quiz.mapper;


import ir.quiz.quiz.dto.response.CourseStudentResponse;
import ir.quiz.quiz.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseResponseStudentMapper extends BaseMapper<Course, CourseStudentResponse> {
}
