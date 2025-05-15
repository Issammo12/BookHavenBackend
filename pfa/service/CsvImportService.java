package org.example.pfa.service;

import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import org.example.pfa.dao.entities.Book;
import org.example.pfa.dao.repositories.BookRepository;
import org.example.pfa.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.lang.Long.parseLong;

@Service
public class CsvImportService {
    @Autowired
    private BookRepository bookRepository;



    @PostConstruct
    public void importCsv() throws IOException {

        try {
            var resource = new ClassPathResource("data.csv");
            var reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);

            List<BookDTO> csvBooks = new CsvToBeanBuilder<BookDTO>(reader)
                    .withType(BookDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

            csvBooks.forEach(csvBook -> {
                Book book = new Book();
                book.setIsbn10(csvBook.getIsbn10());
                book.setTitle(csvBook.getTitle());
                book.setAuthor(csvBook.getAuthor());
                book.setDescription(csvBook.getDescription());
                book.setNumberOfPages(csvBook.getNumberOfPages());
                book.setDatePublished(csvBook.getDatePublished());
                book.setIsbn13(csvBook.getIsbn13());
                book.setThumbnail(csvBook.getThumbnail());
                book.setCategory(csvBook.getCategory());
                bookRepository.save(book);
            });

        } catch (Exception e) {
            System.err.println("Erreur d'import CSV : " + e.getMessage());
        }
    }
}

