package org.example.pfa.dao.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer noteId;
    private Long rating;
    @OneToOne
    @JoinColumn(name = "isbn13")
    private Book book;
    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;
}
