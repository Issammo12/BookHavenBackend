package org.example.pfa.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "playlist")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Date creationDate;
    @ManyToOne
    private User user;
    @OneToMany
    private List<Book> books;

}
