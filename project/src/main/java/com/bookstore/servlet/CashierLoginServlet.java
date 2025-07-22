package com.bookstore.servlet;

import com.bookstore.dao.CashierDAO;
import com.bookstore.model.CashierEmployee;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class CashierLoginServlet extends HttpServlet {
    private CashierDAO cashierDAO;

    @Override
    public void init() throws ServletException {
        cashierDAO = new CashierDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        CashierEmployee cashier = cashierDAO.getCashierByUsername(username);
        
        if (cashier != null && cashier.authenticate(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("cashier", cashier);
            response.sendRedirect("cashier-dashboard");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("cashier-login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("cashier-login.jsp").forward(request, response);
    }
}