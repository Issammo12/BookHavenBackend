package org.example.pfa.service;

import org.example.pfa.dao.entities.Note;
import org.example.pfa.dao.entities.User;
import org.example.pfa.dao.repositories.NoteRepository;
import org.example.pfa.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NoteService implements NoteManager{
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    UserRepository userRepo;
    @Override
    public Note addNote(Note note) {
        Note n= noteRepository.findById(note.getNoteId()).get();
        if (n==null){
            System.out.println("Note est ajoutée");
            return noteRepository.save(note);
        }
        return null;
    }

    @Override
    public Note updateNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public boolean deleteNote(Integer id) {
        Note n= noteRepository.findById(id).get();
        if (n!=null){
            noteRepository.delete(n);
            System.out.println("Note est supprimée");
            return true;
        }
        return false;
    }

    @Override
    public List<Note> getNotes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("connectez vous ");
        }
        String username = authentication.getName();
        User user = userRepo.findByName(username);
        return noteRepository.findNotesByUser(user);
    }

    @Override
    public Note getNote(int id) {
        return noteRepository.findById(id).get();
    }
}
