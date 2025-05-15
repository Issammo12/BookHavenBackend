package org.example.pfa.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Playlist> playlits;
    @ManyToMany(mappedBy = "users")
    private List<Club> clubs;
//    @OneToMany(mappedBy = "user")
//    private List<Book> recommendedbooks;
}
