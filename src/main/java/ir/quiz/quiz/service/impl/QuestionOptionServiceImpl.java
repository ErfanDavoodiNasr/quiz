package ir.quiz.quiz.service.impl;


import ir.quiz.quiz.dto.request.QuestionOptionRequest;
import ir.quiz.quiz.dto.request.QuestionOptionUpdateRequest;
import ir.quiz.quiz.dto.response.QuestionOptionResponse;
import ir.quiz.quiz.exception.QuestionNotFoundException;
import ir.quiz.quiz.exception.QuestionOptionNotFoundException;
import ir.quiz.quiz.mapper.QuestionOptionRequestMapper;
import ir.quiz.quiz.mapper.QuestionOptionResponseMapper;
import ir.quiz.quiz.model.quiz.MultipleChoiceQuestion;
import ir.quiz.quiz.model.quiz.QuestionOption;
import ir.quiz.quiz.repository.MultipleChoiceQuestionRepository;
import ir.quiz.quiz.repository.QuestionOptionRepository;
import ir.quiz.quiz.service.QuestionOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionOptionServiceImpl implements QuestionOptionService {

    private final QuestionOptionRepository questionOptionRepository;
    private final QuestionOptionResponseMapper questionOptionResponseMapper;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final QuestionOptionRequestMapper questionOptionRequestMapper;

    @Override
    public Boolean save(QuestionOptionRequest questionOption) {
        QuestionOption result = questionOptionRepository.save(questionOptionRequestMapper.convertDtoToEntity(questionOption));
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public QuestionOptionResponse update(QuestionOptionUpdateRequest req) {
        Optional<QuestionOption> questionOption = questionOptionRepository.findById(req.getId());
        if (questionOption.isEmpty()) {
            throw new QuestionOptionNotFoundException("no question option found");
        }
        Optional<MultipleChoiceQuestion> multipleChoiceQuestion = multipleChoiceQuestionRepository.findById(req.getMultipleChoiceQuestionId());
        if (multipleChoiceQuestion.isEmpty()) {
            throw new QuestionNotFoundException("no multiple question found");
        }
        questionOption.get().setMultipleChoiceQuestion(multipleChoiceQuestion.get());
        questionOption.get().setText(req.getText());
        questionOption.get().setIsCorrect(req.getIsCorrect());
        QuestionOption result = questionOptionRepository.save(questionOption.get());
        return questionOptionResponseMapper.convertEntityToDto(result);
    }

    @Override
    public Boolean remove(Long id) {
        try {
            questionOptionRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Optional<List<QuestionOptionResponse>> findAll() {
        List<QuestionOption> result = questionOptionRepository.findAll();
        if (result.isEmpty()) {
            throw new QuestionOptionNotFoundException("no question option found");
        }
        return Optional.ofNullable(questionOptionResponseMapper.convertEntityToDto(result));
    }

    @Override
    public Optional<QuestionOptionResponse> findById(Long id) {
        Optional<QuestionOption> option = questionOptionRepository.findById(id);
        if (option.isEmpty()) {
            throw new QuestionOptionNotFoundException("no question option found");
        }
        return Optional.ofNullable(questionOptionResponseMapper.convertEntityToDto(option.get()));
    }
}
