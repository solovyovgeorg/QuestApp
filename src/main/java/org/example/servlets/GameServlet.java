package org.example.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import org.example.dto.Config;
import org.example.services.QuestionService;
import org.example.exceptions.QuestionNotFoundException;
import org.example.model.Question;
import java.io.IOException;
import java.util.List;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private QuestionService questionService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        questionService = new QuestionService();
        questionService.initByConfig(new Config());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (session.getAttribute("playerName") == null) {
            session.setAttribute("playerName", "Неизвестный");
        }
        if (session.getAttribute("state") == null) {
            session.setAttribute("state",1);
        }
        String stateValue = session.getAttribute("state").toString();
        int state = Integer.parseInt(stateValue);
        Question question = null;
        List<Question> variants = null;
        try {
            question = questionService.getQuestionById(state);
            variants = questionService.getVatiantsByQuestion(question);
        } catch (QuestionNotFoundException e) {
            resp.getWriter().println("question not found");
            return;
        }
        req.setAttribute("question", question);
        req.setAttribute("variants", variants);
        req.getRequestDispatcher("/WEB-INF/game.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
            if (req.getParameter("nextState") == null) {
                String name = req.getParameter("playerName");
                String state = req.getParameter("state");
                session.setAttribute("state", state);
                session.setAttribute("playerName", name);
                resp.sendRedirect("/game");
                return;
            }

            if (req.getParameter("nextState") != null) {
                int nextState = Integer.parseInt(req.getParameter("nextState"));
                session.setAttribute("state", nextState);
            }
        resp.sendRedirect("/game");
    }
}
