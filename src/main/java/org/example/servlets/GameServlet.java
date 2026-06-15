package org.example.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.QuestionService;
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

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        Integer state = (Integer) session.getAttribute("state");

        if (state == null) {
            state = 1;
            session.setAttribute("state", state);
        }

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
        req.getRequestDispatcher("/WEB-INF/game.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int nextState = Integer.parseInt(req.getParameter("nextState"));
        session.setAttribute("state", nextState);
        resp.sendRedirect("/game");
    }
}
