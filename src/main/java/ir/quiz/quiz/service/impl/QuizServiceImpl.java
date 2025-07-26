package ir.quiz.quiz.service.impl;

import ir.quiz.quiz.dto.request.QuizRequest;
import ir.quiz.quiz.mapper.QuizRequestMapper;
import ir.quiz.quiz.model.Quiz;
import ir.quiz.quiz.repository.QuizRepository;
import ir.quiz.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static ir.quiz.quiz.util.Help.checkTimeIsValid;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizRequestMapper quizRequestMapper;

    @Override
    public Boolean save(QuizRequest quizRequest) {
        Quiz quiz = quizRequestMapper.convertDtoToEntity(quizRequest);
        checkTimeIsValid(quiz.getStartAt(), quiz.getEndAt());
        Quiz result = quizRepository.save(quiz);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Optional<Quiz> findById(Long id) {
        if (id != null) {
            return quizRepository.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Quiz> findByCourseId(Long courseId) {
        return quizRepository.findByCourse_Id(courseId);
    }

    @Override
    public Optional<Quiz> findReferenceById(Long id) {
        if (id != null) {
            return Optional.ofNullable(quizRepository.getReferenceById(id));
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Quiz>> findAllByCourseId(Number courseId) {
        if (courseId != null) {
            return quizRepository.findAllByCourse_Id(courseId);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Quiz>> findAll() {
        return Optional.ofNullable(quizRepository.findAll());
    }
}
