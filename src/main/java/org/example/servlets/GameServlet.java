package org.example.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import org.example.services.QuestionService;
import org.example.exceptions.QuestionNotFoundException;
import org.example.model.Question;

import java.io.IOException;
import java.util.List;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private QuestionService questionService = new QuestionService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("playerName") == null ||
                session.getAttribute("state") == null) {
            req.getRequestDispatcher("/WEB-INF/noauth.jsp").forward(req, resp);
            return;
        }
        String stateValue = session.getAttribute("state").toString();
        int state = Integer.parseInt(stateValue);
        Question question = null;
        List<Question> variants = null;
        try {
            question = questionService.getQuestionById(state);
            variants = questionService.getVatiantsByQuestion(question);
        } catch (QuestionNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/errors.jsp").forward(req, resp);
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
        String path = req.getContextPath();
        if (req.getParameter("nextState") == null) {
            session.setAttribute("state", 1);
            String name = req.getParameter("playerName");
            session.setAttribute("playerName", name);
            resp.sendRedirect(path + "/game");
            return;
        }
        int nextState = Integer.parseInt(req.getParameter("nextState"));
        session.setAttribute("state", nextState);
        resp.sendRedirect(path + "/game");


    }
}
