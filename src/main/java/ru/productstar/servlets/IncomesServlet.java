package ru.productstar.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.productstar.servlets.model.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/incomes/add")
public class IncomesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var context = req.getServletContext();

        var session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("/");
            return;
        }
        int freeMoney = (int) context.getAttribute("freeMoney");
        var transaction = new ArrayList<Transaction>((List) context.getAttribute("transaction"));
        try {
        for (var k : req.getParameterMap().keySet()) {
                int value = Integer.parseInt(req.getParameter(k));
            freeMoney += value;
            transaction.add(new Transaction(k, value));
        } } catch (NumberFormatException e) {
            resp.getWriter().println("Failed to get number.");
            return;
        }

        context.setAttribute("transaction", transaction);
        context.setAttribute("freeMoney", freeMoney);
        resp.sendRedirect("/summary");
    }
}


