package org.example.pfa.service;

import org.example.pfa.dao.entities.Note;

import java.util.List;

public interface NoteManager {
    public Note addNote(Note note);
    public Note updateNote(Note note);
    public boolean deleteNote(Integer id);
    public List<Note> getNotes();
    public Note getNote(int id);
}
