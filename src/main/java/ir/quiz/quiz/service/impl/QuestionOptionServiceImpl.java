package ir.quiz.quiz.service.impl;


import ir.quiz.quiz.model.quiz.QuestionOption;
import ir.quiz.quiz.repository.QuestionOptionRepository;
import ir.quiz.quiz.service.QuestionOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionOptionServiceImpl implements QuestionOptionService {

    private final QuestionOptionRepository questionOptionRepository;

    @Override
    public Boolean save(QuestionOption questionOption) {
        QuestionOption result = questionOptionRepository.save(questionOption);
        return result.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }
}
