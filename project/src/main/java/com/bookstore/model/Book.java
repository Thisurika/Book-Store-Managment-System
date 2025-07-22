package com.bookstore.model;

public class Book extends Cashier {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private double price;
    private String imageUrl;
    private int quantity;

    // Default constructor
    public Book() {
        super();
    }

    // Constructor for book-specific data
    public Book(String bookId, String title, String author, String genre, double price, String imageUrl, int quantity) {
        super();
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    // Book-specific getters
    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    // Book-specific setters
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getRole() {
        return "BOOK";
    }

    // Method to calculate total price for multiple copies
    public double calculateTotalPrice(int copies) {
        return price * copies;
    }

    @Override
    public String toString() {
        return bookId + "," + title + "," + author + "," + genre + "," + price + "," + imageUrl + "," + quantity;
    }

    // Static method to create Book from string data
    public static Book fromString(String data) {
        String[] parts = data.split(",");
        if (parts.length >= 7) {
            return new Book(parts[0], parts[1], parts[2], parts[3], 
                          Double.parseDouble(parts[4]), parts[5], Integer.parseInt(parts[6]));
        }
        return null;
    }
}