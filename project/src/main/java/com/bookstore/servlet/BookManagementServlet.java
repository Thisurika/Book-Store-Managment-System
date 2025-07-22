package com.bookstore.servlet;

import com.bookstore.dao.BookDAO;
import com.bookstore.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookManagementServlet extends HttpServlet {
    private BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String bookId = request.getParameter("bookId");
        
        if ("edit".equals(action) && bookId != null) {
            Book book = bookDAO.getBookById(bookId);
            request.setAttribute("book", book);
            request.getRequestDispatcher("edit-book.jsp").forward(request, response);
        } else {
            response.sendRedirect("cashier-dashboard");
        }
    }
}