package org.example.pfa.service;

import org.example.pfa.dao.entities.Book;
import org.example.pfa.dao.entities.Note;
import org.example.pfa.dao.entities.User;
import org.example.pfa.dao.repositories.BookRepository;
import org.example.pfa.dao.repositories.NoteRepository;
import org.example.pfa.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements BookManager{
    @Autowired
    BookRepository bookRepo;
    @Autowired
    NoteRepository noteRepo;
    @Autowired
    UserRepository userRepo;
    @Override
    public Book getBook(String id) {
        return bookRepo.findById(id).get();
    }

    @Override
    public List<Book> getRelatedBooks(String id) throws IOException, InterruptedException {
        Book book = bookRepo.findById(id).get();
        ProcessBuilder processBuilder = new ProcessBuilder("python3", "C:/Users/hp/Desktop/PFA/PFA/src/main/resources/ai", book.getTitle());

        // Start the process
        Process process = processBuilder.start();

        // Read output from Python script
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        List<String> list;
        List<Book> books = new ArrayList<>();
        while ((list = Collections.singletonList(reader.readLine())) != null) {
            for (String s : list) {
                books.add(bookRepo.findByTitle(s));
            }
        }
        return books;
    }

    @Override
    public List<Book> getAllBooks() throws IOException {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            System.out.println("connectez vous ");
//        }
//        String username = authentication.getName();
//        User user = userRepo.findByName(username);
//        List<Note> notes = noteRepo.findNotesByUser(user);
//
//        List<Book> books =notes.stream().map(note -> note.getBook()).collect(Collectors.toList());
//        if (books == null) {
//            return bookRepo.findAll();
//        }
//        else {
//            List<Book> similarBooks = new ArrayList<>();
//            for (Book book : books) {
//                ProcessBuilder processBuilder = new ProcessBuilder("python3", "C:/Users/hp/Desktop/PFA/PFA/src/main/resources/ai", book.getTitle());
//
//                // Start the process
//                Process process = processBuilder.start();
//
//                // Read output from Python script
//                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                List<String> list;
//
//                while ((list = Collections.singletonList(reader.readLine())) != null) {
//                    for (String s : list) {
//                        similarBooks.add(bookRepo.findByTitle(s));
//                    }
//                }
//            }
//            return books;
//        }
        return bookRepo.findAll();

    }

    @Override
    public Book addBook(Book book) {
        Book b=bookRepo.findById(book.getIsbn13()).get();
        if(b == null) {
            System.out.println("Ajout effectué");
            return bookRepo.save(book);
        }
        return null;

    }

    @Override
    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public boolean deleteBook(String id) {
        Book book = bookRepo.findById(id).get();
        if(book != null) {
            bookRepo.delete(book);
            System.out.println("suppression effectué");
            return true;
        }
        return false;
    }
}
