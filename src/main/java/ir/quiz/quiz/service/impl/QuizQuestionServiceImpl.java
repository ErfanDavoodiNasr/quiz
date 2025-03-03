package ir.quiz.quiz.service.impl;


import ir.quiz.quiz.dto.request.QuizQuestionRequest;
import ir.quiz.quiz.exception.QuestionNotFoundException;
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

    private Optional<Question> checkQuestionIsExist(QuizQuestionRequest quizQuestion) {
        Optional<Question> question = questionRepository.findById(quizQuestion.getQuestionId());
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("no question found");
        }
        return question;
    }
}
