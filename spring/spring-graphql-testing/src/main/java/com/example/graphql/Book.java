package com.example.graphql;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public record Book(String id, String name, int pageCount, String authorId) {

    private static Random random = new SecureRandom();

    private static Map<String, Book> books = Collections.synchronizedMap(new HashMap<String, Book>());

    static {
        books.put("book-1", new Book("book-1", "Effective Java", 416, "author-1"));
        books.put("book-2", new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"));
        books.put("book-3", new Book("book-3", "Down Under", 436, "author-3"));
    }

    public static Book getById(String id) {
        synchronized (books) {
            return books.getOrDefault(id, null);
        }
    }

    public static List<Book> getAll() {
        synchronized (books) {
            return books.values().stream().toList();
        }
    }

    public static Book add(String name, int pageCount, String authorId) {
        var id = "book-%d".formatted(random.nextInt());
        var book = new Book(id, name, pageCount, authorId);
        synchronized (books) {
            if (!books.containsKey(book.id)) {
                books.put(book.id, book);
            }
        }
        return book;
    }

    public static Book edit(Book book) {
        synchronized (books) {
            if (books.containsKey(book.id)) {
                books.put(book.id, book);
            }
        }
        return book;
    }

    public static boolean remove(String id) {
        synchronized (books) {
            return books.remove(id) != null;
        }
    }

}