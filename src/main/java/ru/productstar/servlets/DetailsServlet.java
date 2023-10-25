package ru.productstar.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.productstar.servlets.model.Transaction;

import java.io.IOException;
import java.util.List;

public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var context = req.getServletContext();

        var session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("/");
            return;
        }

        resp.getWriter().println("Transaction: ");
        for (Transaction e : (List<Transaction>) context.getAttribute("transaction")) {
            resp.getWriter().println(String.format(" %s(%d)", e.getName(), e.getSum()));
        }
        resp.getWriter().println("\n");
    }
}
