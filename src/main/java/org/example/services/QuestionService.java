package org.example.services;

import data.QuestionRepository;
import org.example.exceptions.QuestAppException;
import org.example.model.Question;
import java.util.List;


/** Сервис не работает с данными напрямую, делегирует запросы данных репозиторию*/
public class QuestionService {
    private QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Question getQuestionById(int id) throws QuestAppException {
        return repository.getQuestionById(id);
    }

    public List<Question> getVariantsByQuestion(Question question) throws QuestAppException {
        return repository.getVariantsByQuestion(question);
    }



}

