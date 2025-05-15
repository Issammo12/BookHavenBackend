package org.example.pfa;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.example.pfa.dao.entities.Book;
import org.example.pfa.dao.entities.User;

import org.example.pfa.dao.repositories.BookRepository;
import org.example.pfa.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.Objects;

@SpringBootApplication
public class PfaApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(PfaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User a =new User();
        User b =new User();

        a.setIdUser(null);
        a.setName("issam");
        a.setEmail("issam@gmail.com");
        a.setPassword("1234");
        b.setIdUser(null);
        b.setName("ana");
        b.setEmail("ana@gmail.com");
        b.setPassword("1234");


//        ClassPathResource resource = new ClassPathResource("data.csv");
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                Book book = new Book();
//                String[] fields = line.split(",");
//
//                if (fields.length >= 9) {
//                    book.setIdBook(null);
//                    int isbn13 = Integer.parseInt(fields[0].trim());
//                    int isbn10 = Integer.parseInt(fields[1].trim());
//                    String title = fields[2].trim();
//                    String author = fields[4].trim();
//                    String category = fields[5].trim();
//                    String thumbnail = fields[6].trim();
//                    String description = fields[7].trim();
//                    Date published = new Date(Long.parseLong(fields[8].trim()));
//                    int numberPages = Integer.parseInt(fields[10].trim());
//                    int numberReviews = Integer.parseInt(fields[11].trim());
//                    book.setIsbn13(isbn13);
//                    book.setIsbn10(isbn10);
//                    book.setTitle(title);
//                    book.setAuthor(author);
//                    book.setCategory(category);
//                    book.setThumbnail(thumbnail);
//                    book.setDescription(description);
//                    book.setDatePublished(published);
//                    book.setNumberOfPages(numberPages);
//
//
//                    bookRepository.save(book);
//
//
//                }
//            }
//        }

//        userRepository.save(a);
//        userRepository.save(b);


    }
}
