package org.example.pfa.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "club")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String category;
    @ManyToMany
    private List<User> users;
    @ManyToMany(mappedBy = "clubs")
    private List<Book> books;

}
