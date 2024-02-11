package com.java;

import java.util.HashMap;
import java.util.Map;

public class LibraryManagementSystem {
    private static Map<String, Integer> bookStock = new HashMap<>();

    public static void main(String[] args) {

        try {
            //kitap eklemek
            bookAdd("Java Programming", 5);
            bookAdd("Python Essentials", 3);

            //Geçersiz kitap eklemek (Stok negatif olamaz)
            bookAdd("C# Fundamentals", -2);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + " " + e.getMessage());
        }
        try {
            //kitap ödünç almak
            borrowABook("Java Programming", 2);
            borrowABook("Python Essentials", 4);
            borrowABook("Python Essentials", 1);

        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + " " + e.getMessage());
        }
        try {
            // Olmayan kitabı ödünç almaya çalışmak
            borrowABook("C# Fundamentals", 2);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            //kitap iadesi yapmak
            bookReturn("Java Programming", 1);
            bookReturn("Python Essentials", 1);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + " " + e.getMessage());
        }

    }

    private static void bookAdd(String bookName, int quantityStock) {
        if (quantityStock < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        bookStock.put(bookName, quantityStock);
        System.out.println(bookName + " " + "added to library. Stock quantity: "
                + " " + quantityStock);
    }

    // kitap ödünç almak
    private static void borrowABook(String bookName, int quantity) throws BookNotFoundException {
        if (!bookStock.containsKey(bookName)) {
            throw new BookNotFoundException(bookName + " " + "Not found in library");
        }

        int quantityStock = bookStock.get(bookName);
        if (quantity > quantityStock) {
            throw new IllegalArgumentException("There is not enough stock. " +
                    "Current stock quantity: " + quantityStock);
        }

        bookStock.put(bookName, quantityStock - quantity);
        System.out.println(quantity + " quantity " + bookName + " The book was borrowed." +
                " Number of remaining stock: " + (quantityStock - quantity));
    }

    // kitap iadesi yapmak
    private static void bookReturn(String bookName, int quantity) throws BookNotFoundException {
        if (!bookStock.containsKey(bookName)) {
            throw new BookNotFoundException(bookName + "Not found in library");
        }

        int quantityStock = bookStock.get(bookName);
        bookStock.put(bookName, (quantityStock + quantity));
        System.out.println(quantity + " quantity " + bookName +
                " the book was returned. New stock quantity: " + (quantityStock + quantity));
    }
}