package org.example.pfa.service;

import org.example.pfa.dao.entities.Book;
import org.example.pfa.dao.entities.Club;
import org.example.pfa.dao.entities.Playlist;
import org.example.pfa.dao.entities.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface UserManager {
    public boolean login(String email, String password);

    public User updateUser(User user);
    public boolean deleteUser(Integer id);
    public User addUser(User user);
    public <T> User getUser(T par);
    public List<User> getUsers();
    public List<Club> getClubs();
    public String rejoindreClub(Integer clubId);
    public HashMap<User , Book> reccomenderLivre(String livreId);
    public String ajouterLivreAuPlaylist(String livreId , Integer playlistId);

    public String creerPlaylist(Playlist playlist);

    public List<Book> searchBook(String search);

//    public List<Book> similarBooks(String title) throws IOException;

//    public List<Book> getBooks();
    public List<Playlist> getPlaylists();
}
