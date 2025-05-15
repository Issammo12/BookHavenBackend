package org.example.pfa.dao.entities;

import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book{
    @Id
//    private Integer idBook;
    private String isbn13;
    private String isbn10;
    @Column(columnDefinition="TEXT")
    private String title;
    private String author;
    @Column(columnDefinition="TEXT")
    private String description;
    private String category;
    private String thumbnail;
    private Date datePublished;
    private int numberOfPages;
    @ManyToMany
    private List<Club> clubs;
//    @ManyToOne
//   @JoinColumn(name = "idUser")
//    private User user;
}
