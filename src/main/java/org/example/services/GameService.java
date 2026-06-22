package org.example.services;

import data.QuestionRepository;
import org.example.exceptions.QuestAppException;
import org.example.model.Choice;
import org.example.model.GameView;
import org.example.model.Question;
import java.util.ArrayList;
import java.util.List;


public class GameService {
    private QuestionRepository repository;
    private final String GAME_TITLE_TEXT = "КВЕСТ";
    private final String GAME_H1_TEXT="Дело №34";
    private final String GAME_SUBTITLE="Расследование ведет: ";
    private final String ERROR_TITLE="Ошибка приложения";
    private final String ERROR_MAIN="В приложении возникла ошибка: ";

    public GameService(QuestionRepository repository) {
        this.repository = repository;
    }

    public GameView getGameViewByState(int state) {
       GameView gameView = new GameView();
       try {
           Question question = repository.getQuestionById(state);
           gameView.setTitle_text(GAME_TITLE_TEXT);
           gameView.setH1_text(GAME_H1_TEXT);
           gameView.setSubtitle_text(GAME_SUBTITLE);
           gameView.setMaintext(question.getText());
           gameView.setDescription(question.getDescription());
           gameView.setChoices(getChoicesByQuestion(question));
           return gameView;
       } catch (Exception e) {
           gameView.setTitle_text(ERROR_TITLE);
           gameView.setH1_text(ERROR_MAIN);
           gameView.setSubtitle_text("");
           gameView.setDescription("");
           gameView.setMaintext(e.getMessage());
           return gameView;
       }

    }

    private List<Choice> getChoicesByQuestion(Question question) throws QuestAppException {
        List<Choice> choices = new ArrayList<>();

        for (int variant:question.getVariants()) {
            String title = repository.getQuestionById(variant).getTitle();
            choices.add(new Choice(variant,title));
        }
        return choices;
    }



}

