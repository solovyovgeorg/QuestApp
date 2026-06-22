package org.example.services;

import org.example.model.GameView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
/** Класс обработчик реквестов из сервлета*/
public class RequestHandler {
    private GameService gameService;

    public RequestHandler(GameService gameService) {
        this.gameService = gameService;
    }
    /** Определяет состояние клиента в зависимости от запроса*/
    public ClientState clientStateByRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("playerName") == null) {
            return ClientState.NO_ACCESS_CLIENT;
        }
        return ClientState.ACCESS_CLIENT;
    }
    /** Модифицирует запрос GET для передачи данных в зависимости от текущего gameState для перенаправления в /game.jsp*/
    public void handleGetRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int gameState = 1;
        if (session.getAttribute("gameState") != null) {
            String stateValue = session.getAttribute("gameState").toString();
            gameState = Integer.parseInt(stateValue);
        }
        GameView gameView = gameService.getGameViewByState(gameState);
        request.setAttribute("gameview", gameView);
    }
    /** Сохраняет данные имени и текущего gameState при запросах POST в сессию клиента для последующего редиректа в GET /game */
    public void handlePostRequest (HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        if (request.getParameter("nextState") == null) {
            session.setAttribute("gameState", 1);
            String name = request.getParameter("playerName");
            session.setAttribute("playerName", name);
            return;
        }
        int nextState = Integer.parseInt(request.getParameter("nextState"));
        session.setAttribute("gameState", nextState);


    }

}
