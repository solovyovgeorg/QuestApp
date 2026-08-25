package data;

import org.example.config.ConfigLoader;
import org.example.dto.QuestConfig;
import org.example.exceptions.QuestAppException;
import org.example.model.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionRepositoryImpl implements QuestionRepository{
    private Map<Integer,Question> questionMap;

    public QuestionRepositoryImpl () throws RuntimeException{

        questionMap = new HashMap<>();
        initByConfig(new QuestConfig());
    }
    @Override
    public Question getQuestionById(int id) throws QuestAppException {
        if (questionMap.isEmpty()) {
            throw new QuestAppException("Question cannot be empty, check App properties");
        }
        if (questionMap.get(id) == null) {
            throw new QuestAppException("Question cannot be null");
        }
        return questionMap.get(id);
    }

    private void initByConfig(QuestConfig config) throws RuntimeException {
        ConfigLoader loader = new ConfigLoader(config.getClass());
        try {
            loader.loadFromFile("config.json");
            config = (QuestConfig) loader.getConfig();
            questionMap.putAll(config.getQuestionsMap());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
