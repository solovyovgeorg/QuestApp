package org.example.services;

import org.example.exceptions.QuestAppException;
import org.example.model.Question;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

/** Класс обработчик реквестов из сервлета*/
public class RequestHandler {
    private QuestionService questionService;

    public RequestHandler(QuestionService questionService) {
        this.questionService = questionService;
    }
    /** Для удобства перенаправлений в сервлете определяем состояние клиента*/
    public ClientState clientStateByRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("playerName") == null) {
            return ClientState.NO_ACCESS_CLIENT;
        }
        return ClientState.ACCESS_CLIENT;
    }
    /** Модифицирует запрос GET для передачи данных в зависимости от текущего state для перенаправления в /game.jsp*/
    public void editRequest(HttpServletRequest request) throws QuestAppException {
        HttpSession session = request.getSession();
        Question question = null;
        List<Question> variants = null;
        int gameState = 1;
        if (session.getAttribute("state") != null) {
            String stateValue = session.getAttribute("state").toString();
            gameState = Integer.parseInt(stateValue);
        }
        question = questionService.getQuestionById(gameState);
        variants = questionService.getVariantsByQuestion(question);
        request.setAttribute("question", question);
        request.setAttribute("variants", variants);
    }
    /** Сохраняет данные имени и текущего state при запросах POST в сессию клиента для последующего редиректа в GET /game */
    public void editSession (HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String path = request.getContextPath();
        if (request.getParameter("nextState") == null) {
            session.setAttribute("state", 1);
            String name = request.getParameter("playerName");
            session.setAttribute("playerName", name);
            return;
        }
        int nextState = Integer.parseInt(request.getParameter("nextState"));
        session.setAttribute("state", nextState);


    }

}
