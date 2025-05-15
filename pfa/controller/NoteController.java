package org.example.pfa.controller;

import org.example.pfa.dao.entities.Note;
import org.example.pfa.service.NoteManager;
import org.example.pfa.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteManager noteService;

    @GetMapping("/listNotes")
    public List<Note> listNotes() {
        List<Note> notes=noteService.getNotes();
        return notes;
    }

    @PostMapping("/addNote")
    public String addNote(Note note) {
        Note newNote=noteService.addNote(note);
        if(newNote==null) return "error";
        else return "Note added successfully";
    }

    @PutMapping("/updateNote")
    public String updateNote(Note note) {
        Note newNote=noteService.updateNote(note);
        if(newNote==null) return "error";
        return "Note updated successfully";
    }

    @DeleteMapping("/deleteNote")
    public String deleteNote(Integer id ) {
        boolean check = noteService.deleteNote(id);
        if(check){
            return "Note deleted successfully";
        }
        return "error";
    }

}
