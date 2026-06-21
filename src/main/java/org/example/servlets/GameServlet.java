package org.example.servlets;

import data.QuestionRepositoryImpl;
import org.example.services.ClientState;
import org.example.exceptions.QuestAppException;
import org.example.services.QuestionService;
import org.example.services.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private RequestHandler requestHandler = new RequestHandler(new QuestionService(new QuestionRepositoryImpl()));


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*Определяем состояние клиента при запросе и производим необходимые перенаправления*/
        ClientState clientState = requestHandler.clientStateByRequest(req);

        switch (clientState) {

            case NO_ACCESS_CLIENT:
                req.getRequestDispatcher("/WEB-INF/noauth.jsp").forward(req, resp);
                return;
            case ACCESS_CLIENT: {
                try {
                    requestHandler.editRequest(req);
                } catch (QuestAppException e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/errors.jsp").forward(req, resp);
                    return;
                }
                req.getRequestDispatcher("/WEB-INF/game.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
                String path = req.getContextPath();
                requestHandler.editSession(req);
                resp.sendRedirect(path + "/game");
    }
}
