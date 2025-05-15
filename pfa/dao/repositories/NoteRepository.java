package org.example.pfa.dao.repositories;

import org.example.pfa.dao.entities.Book;
import org.example.pfa.dao.entities.Note;
import org.example.pfa.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findNotesByBook(Book book);

    List<Note> findNotesByUser(User user);

}
