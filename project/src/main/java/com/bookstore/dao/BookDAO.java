package com.bookstore.dao;

import com.bookstore.model.Book;
import com.bookstore.utils.FileHandler;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private static final String BOOK_FILE = "books.txt";

    public boolean addBook(Book book) {
        String data = book.toString() + "\n";
        return FileHandler.writeToFile(BOOK_FILE, true, data);
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String[] lines = FileHandler.readFromFile(BOOK_FILE);
        
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                Book book = Book.fromString(line);
                if (book != null) {
                    books.add(book);
                }
            }
        }
        return books;
    }

    public Book getBookById(String bookId) {
        List<Book> books = getAllBooks();
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public boolean updateBook(Book updatedBook) {
        List<Book> books = getAllBooks();
        StringBuilder data = new StringBuilder();
        
        for (Book book : books) {
            if (book.getBookId().equals(updatedBook.getBookId())) {
                data.append(updatedBook.toString()).append("\n");
            } else {
                data.append(book.toString()).append("\n");
            }
        }
        
        return FileHandler.writeToFile(BOOK_FILE, false, data.toString());
    }

    public boolean deleteBook(String bookId) {
        List<Book> books = getAllBooks();
        StringBuilder data = new StringBuilder();
        
        for (Book book : books) {
            if (!book.getBookId().equals(bookId)) {
                data.append(book.toString()).append("\n");
            }
        }
        
        return FileHandler.writeToFile(BOOK_FILE, false, data.toString());
    }

    public List<Book> getBooksByGenre(String genre) {
        List<Book> allBooks = getAllBooks();
        List<Book> filteredBooks = new ArrayList<>();
        
        for (Book book : allBooks) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                filteredBooks.add(book);
            }
        }
        
        return filteredBooks;
    }
}