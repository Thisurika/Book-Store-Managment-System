package com.bookstore.servlet;

import com.bookstore.dao.CashierDAO;
import com.bookstore.model.CashierEmployee;
import com.bookstore.model.Manager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ManagerDashboardServlet extends HttpServlet {
    private CashierDAO cashierDAO;

    @Override
    public void init() throws ServletException {
        cashierDAO = new CashierDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
        
        if (manager == null) {
            response.sendRedirect("manager-login");
            return;
        }

        String action = request.getParameter("action");
        
        if ("logout".equals(action)) {
            session.invalidate();
            response.sendRedirect("index.jsp");
            return;
        }

        List<CashierEmployee> cashiers = cashierDAO.getAllCashiers();
        request.setAttribute("cashiers", cashiers);
        request.getRequestDispatcher("manager-dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
        
        if (manager == null) {
            response.sendRedirect("manager-login");
            return;
        }

        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            addCashier(request, response);
        } else if ("update".equals(action)) {
            updateCashier(request, response);
        } else if ("delete".equals(action)) {
            deleteCashier(request, response);
        }
        
        response.sendRedirect("manager-dashboard");
    }

    private void addCashier(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String employeeId = request.getParameter("employeeId");
        String department = request.getParameter("department");

        CashierEmployee cashier = new CashierEmployee(id, username, password, name, email, employeeId, department);
        cashierDAO.addCashier(cashier);
    }

    private void updateCashier(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String employeeId = request.getParameter("employeeId");
        String department = request.getParameter("department");

        CashierEmployee cashier = new CashierEmployee(id, username, password, name, email, employeeId, department);
        cashierDAO.updateCashier(cashier);
    }

    private void deleteCashier(HttpServletRequest request, HttpServletResponse response) {
        String cashierId = request.getParameter("cashierId");
        cashierDAO.deleteCashier(cashierId);
    }
}