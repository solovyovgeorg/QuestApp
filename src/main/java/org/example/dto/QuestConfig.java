package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.model.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestConfig {
    @JsonProperty("questions")
    private List<QuestionDTO> questionDTOList;
    private Map<Integer, Question> questionMap;
    public QuestConfig (){}

    public Map<Integer,Question> getQuestionsMap() {
        questionMap = new HashMap<>();
        for (QuestionDTO dto:questionDTOList) {
            Question question = new Question(dto.getId(),dto.getText(),dto.getTitle(),dto.getDescription(),dto.getVariants());
            questionMap.put(question.getId(),question);
        }
        return questionMap;
    }
}
