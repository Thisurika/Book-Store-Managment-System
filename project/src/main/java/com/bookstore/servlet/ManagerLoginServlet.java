package com.bookstore.servlet;

import com.bookstore.dao.ManagerDAO;
import com.bookstore.model.Manager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class ManagerLoginServlet extends HttpServlet {
    private ManagerDAO managerDAO;

    @Override
    public void init() throws ServletException {
        managerDAO = new ManagerDAO();
        // Initialize default manager
        managerDAO.initializeDefaultManager();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Manager manager = managerDAO.getManagerByUsername(username);
        
        if (manager != null && manager.authenticate(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("manager", manager);
            response.sendRedirect("manager-dashboard");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("manager-login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("manager-login.jsp").forward(request, response);
    }
}