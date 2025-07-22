package com.bookstore.utils;

import com.bookstore.model.Book;
import java.util.List;
import java.util.ArrayList;

public class MergeSort {
    
    public static List<Book> sortBooksByGenre(List<Book> books, String targetGenre) {
        // First filter books by genre
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(targetGenre)) {
                filteredBooks.add(book);
            }
        }
        
        // Then sort the filtered books by title using merge sort
        if (filteredBooks.size() <= 1) {
            return filteredBooks;
        }
        
        return mergeSort(filteredBooks);
    }
    
    private static List<Book> mergeSort(List<Book> books) {
        if (books.size() <= 1) {
            return books;
        }
        
        int mid = books.size() / 2;
        List<Book> left = new ArrayList<>(books.subList(0, mid));
        List<Book> right = new ArrayList<>(books.subList(mid, books.size()));
        
        left = mergeSort(left);
        right = mergeSort(right);
        
        return merge(left, right);
    }
    
    private static List<Book> merge(List<Book> left, List<Book> right) {
        List<Book> result = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;
        
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).getTitle().compareToIgnoreCase(right.get(rightIndex).getTitle()) <= 0) {
                result.add(left.get(leftIndex));
                leftIndex++;
            } else {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        
        while (leftIndex < left.size()) {
            result.add(left.get(leftIndex));
            leftIndex++;
        }
        
        while (rightIndex < right.size()) {
            result.add(right.get(rightIndex));
            rightIndex++;
        }
        
        return result;
    }
}