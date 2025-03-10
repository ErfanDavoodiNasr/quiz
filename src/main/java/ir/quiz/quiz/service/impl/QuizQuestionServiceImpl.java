package ir.quiz.quiz.service.impl;


import ir.quiz.quiz.dto.request.QuizQuestionRequest;
import ir.quiz.quiz.dto.response.QuizQuestionResponse;
import ir.quiz.quiz.dto.response.QuizQuestionUpdateRequest;
import ir.quiz.quiz.exception.QuestionNotFoundException;
import ir.quiz.quiz.exception.QuizNotFoundException;
import ir.quiz.quiz.mapper.QuizQuestionResponseMapper;
import ir.quiz.quiz.model.quiz.Question;
import ir.quiz.quiz.model.quiz.QuizQuestion;
import ir.quiz.quiz.repository.QuestionRepository;
import ir.quiz.quiz.repository.QuizQuestionRepository;
import ir.quiz.quiz.service.QuizQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizQuestionServiceImpl implements QuizQuestionService {

    private final QuizQuestionRepository quizQuestionRepository;
    private final QuestionRepository questionRepository;
    private final QuizQuestionResponseMapper quizQuestionResponseMapper;

    private static QuizQuestion convertDtoToEntity(QuizQuestionRequest quizQuestion, Optional<Question> question) {
        return QuizQuestion.builder()
                .question(question.get())
                .score(quizQuestion.getScore())
                .build();
    }

    @Override
    public Boolean save(QuizQuestionRequest quizQuestion) {
        Optional<Question> question = checkQuestionIsExist(quizQuestion);
        QuizQuestion quizQuestion1 = convertDtoToEntity(quizQuestion, question);
        QuizQuestion result = quizQuestionRepository.save(quizQuestion1);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public QuizQuestionResponse update(QuizQuestionUpdateRequest quizQuestionUpdateRequest) {
        Optional<Question> question = questionRepository.findById(quizQuestionUpdateRequest.getQuestionId());
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("no question found");
        }
        Optional<QuizQuestion> quizQuestion = quizQuestionRepository.findById(quizQuestionUpdateRequest.getId());
        if (quizQuestion.isEmpty()) {
            throw new QuizNotFoundException("no quiz question found");
        }
        quizQuestion.get().setQuestion(question.get());
        quizQuestion.get().setScore(quizQuestionUpdateRequest.getScore());
        QuizQuestion result = quizQuestionRepository.save(quizQuestion.get());
        return quizQuestionResponseMapper.convertEntityToDto(result);
    }

    @Override
    public Boolean remove(Long id) {
        try {
            quizQuestionRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    private Optional<Question> checkQuestionIsExist(QuizQuestionRequest quizQuestion) {
        Optional<Question> question = questionRepository.findById(quizQuestion.getQuestionId());
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("no question found");
        }
        return question;
    }
}
