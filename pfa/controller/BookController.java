package org.example.pfa.controller;

import org.example.pfa.dao.entities.Book;
import org.example.pfa.service.BookManager;
import org.example.pfa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/books")
public class  BookController {
    @Autowired
    BookService bookService;
// Pour afficher un livre avec ses informations et deux boutons : pour ajouter le livre dans une playlist , pour ajouter une note
    @GetMapping("getBook/{id}")
    public Book getBook(@PathVariable String id) {
        Book book = bookService.getBook(id);
        return book;
    }
// Pour afficher la liste des livres
    @GetMapping("/allBooks")
    public List<Book> getAllBooks() throws IOException {
        List<Book> books = bookService.getAllBooks();
        return books;
    }
// Pour afficher les livres similaires à un livre
    @GetMapping("/similarBooks/{id}")
    public List<Book> getSimilarBooks(@PathVariable String id) throws IOException, InterruptedException {
        return bookService.getRelatedBooks(id);
    }

    @PostMapping("/addBook")
    public Book addBook(Book book ) {
        Book newBook = bookService.addBook(book);
        return newBook;
    }

    @PutMapping("/updateBook")
    public Book updateBook( Book book ) {
        Book newBook = bookService.updateBook(book);
        return newBook;
    }

    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable String id ) {
        boolean c=bookService.deleteBook(id);
        if (c==true) {
            return bookService.getBook(id).getTitle() + " is deleted";
        }
        else {
            return bookService.getBook(id).getTitle() + " is not deleted";
        }
    }
}
