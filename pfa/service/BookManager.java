package org.example.pfa.service;

import org.example.pfa.dao.entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookManager {
    public Book getBook(String id);
    public List<Book> getRelatedBooks(String id) throws IOException, InterruptedException;
    public List<Book> getAllBooks() throws IOException;
    public Book addBook(Book book);
    public Book updateBook(Book book);
    public boolean deleteBook(String id);
}
