package org.example.servlets;

import data.QuestionRepositoryImpl;
import org.example.services.ClientState;
import org.example.services.GameService;
import org.example.services.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private RequestHandler requestHandler = new RequestHandler(new GameService(new QuestionRepositoryImpl()));


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*Определяем состояние клиента при запросе и производим необходимые перенаправления*/
        ClientState clientState = requestHandler.clientStateByRequest(req);

        switch (clientState) {

            case NO_ACCESS_CLIENT:
                req.getRequestDispatcher("/WEB-INF/noauth.html").forward(req, resp);
                return;
            case ACCESS_CLIENT: {
                requestHandler.handleGetRequest(req);
                req.getRequestDispatcher("/WEB-INF/game.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getContextPath();
        if (req.getParameter("restart") != null) {
            HttpSession session = req.getSession();
            session.invalidate();
            resp.sendRedirect(path);
            return;
        }
        requestHandler.handlePostRequest(req);
        resp.sendRedirect(path + "/game");
    }
}
