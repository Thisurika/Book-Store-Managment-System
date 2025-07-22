package com.bookstore.model;

public class CartItem {
    private Book book;
    private int quantity;

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    // Getters
    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setBook(Book book) {
        this.book = book;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Calculate total price for this cart item
    public double getTotalPrice() {
        return book.getPrice() * quantity;
    }
}