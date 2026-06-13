package org.example;
import org.example.config.ConfigLoader;
import org.example.dto.Config;
import org.example.exceptions.QuestionNotFoundException;
import org.example.model.Question;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QuestionService {
    private Map<Integer, Question> questions;

    public QuestionService() {
        questions = new HashMap<>();
    }

    public Question getQuestionById(int id) throws QuestionNotFoundException {
        if (questions.get(id) == null) {
            throw new QuestionNotFoundException("Question cannot be null");
        }
        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("Question cannot be empty, check App properties");
        }
        return questions.get(id);
    }


    public void initByConfig(Config config) {
        ConfigLoader loader = new ConfigLoader(config.getClass());
        try {
            loader.loadFromFile("config.json");
            config = (Config) loader.getConfig();
            questions.putAll(config.getQuestionsMap());
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

