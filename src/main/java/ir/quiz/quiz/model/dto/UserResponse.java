package ir.quiz.quiz.model.dto;

import ir.quiz.quiz.model.Course;
import ir.quiz.quiz.model.UserRole;

import java.util.List;

public class UserResponse {
    private String firstName;
    private String lastName;
    private String username;
    private List<Course> courses;
    private UserRole userRole;
}
