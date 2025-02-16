package ir.quiz.quiz.service.impl;


import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.dto.CourseRequest;
import ir.quiz.quiz.repository.CourseRepository;
import ir.quiz.quiz.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private static Course courseRequestToCourse(CourseRequest courseRequest) {
        return Course.builder()
                .name(courseRequest.getName())
                .startAt(courseRequest.getStartAt())
                .endAt(courseRequest.getEndAt())
                .build();
    }

    @Override
    public Boolean save(CourseRequest courseRequest) {
        if (courseRequest == null) {
            throw new NullPointerException("course request can't be null");
        }
        Course mappedCourse = courseRequestToCourse(courseRequest);
        Course result = courseRepository.save(mappedCourse);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Course update(Course course) {
        if (course == null | course.getId() != null) {
            throw new NullPointerException("course can't be null");
        }
        return courseRepository.save(course);
    }
}
