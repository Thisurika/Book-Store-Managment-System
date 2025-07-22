package com.bookstore.servlet;

import com.bookstore.dao.BookDAO;
import com.bookstore.model.Book;
import com.bookstore.model.CashierEmployee;
import com.bookstore.utils.MergeSort;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CashierDashboardServlet extends HttpServlet {
    private BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CashierEmployee cashier = (CashierEmployee) session.getAttribute("cashier");
        
        if (cashier == null) {
            response.sendRedirect("cashier-login");
            return;
        }

        String action = request.getParameter("action");
        
        if ("logout".equals(action)) {
            session.invalidate();
            response.sendRedirect("index.jsp");
            return;
        } else if ("search".equals(action)) {
            searchBooksByGenre(request, response);
            return;
        }

        List<Book> books = bookDAO.getAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("cashier-dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CashierEmployee cashier = (CashierEmployee) session.getAttribute("cashier");
        
        if (cashier == null) {
            response.sendRedirect("cashier-login");
            return;
        }

        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            addBook(request, response);
        } else if ("update".equals(action)) {
            updateBook(request, response);
        } else if ("delete".equals(action)) {
            deleteBook(request, response);
        }
        
        response.sendRedirect("cashier-dashboard");
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) {
        String bookId = request.getParameter("bookId");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        double price = Double.parseDouble(request.getParameter("price"));
        String imageUrl = request.getParameter("imageUrl");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Book book = new Book(bookId, title, author, genre, price, imageUrl, quantity);
        bookDAO.addBook(book);
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) {
        String bookId = request.getParameter("bookId");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        double price = Double.parseDouble(request.getParameter("price"));
        String imageUrl = request.getParameter("imageUrl");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Book book = new Book(bookId, title, author, genre, price, imageUrl, quantity);
        bookDAO.updateBook(book);
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        String bookId = request.getParameter("bookId");
        bookDAO.deleteBook(bookId);
    }

    private void searchBooksByGenre(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String genre = request.getParameter("genre");
        List<Book> allBooks = bookDAO.getAllBooks();
        List<Book> sortedBooks = MergeSort.sortBooksByGenre(allBooks, genre);
        
        request.setAttribute("books", sortedBooks);
        request.setAttribute("searchGenre", genre);
        request.getRequestDispatcher("cashier-dashboard.jsp").forward(request, response);
    }
}