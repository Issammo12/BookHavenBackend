package org.example.pfa.dao.repositories;

import org.example.pfa.dao.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book, String> {

    public Book findByTitle(String title);

    List<Book> findByTitleContains(String title);
//    public Book findByAuthor(String author);
//    public List<Book> findAll();
//    public Book findByTitleAndAuthor(String title, String author);
//    public Book addBook(Book book);
//    public Book updateBook(Book book);
//    public boolean deleteBook(Book book);
//    public Book getBook(Integer id);
//    public List<Book> getAllBooks();
}
