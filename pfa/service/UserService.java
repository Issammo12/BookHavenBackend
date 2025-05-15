package org.example.pfa.service;


import org.example.pfa.dao.entities.Book;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.example.pfa.dao.entities.Club;
import org.example.pfa.dao.entities.Playlist;
import org.example.pfa.dao.entities.User;
import org.example.pfa.dao.repositories.BookRepository;
import org.example.pfa.dao.repositories.ClubRepository;
import org.example.pfa.dao.repositories.PlaylistRepository;
import org.example.pfa.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
@Service
public class UserService implements UserManager{
    @Autowired
    UserRepository userRepo;
    ClubRepository clubRepo;
    BookRepository bookRepo;
    PlaylistRepository playlistRepo;
    @Override
    public boolean login(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user!=null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public boolean deleteUser(Integer id) {
        User user = userRepo.findById(id).get();
        if(user != null){
            userRepo.delete(user);
            System.out.println("utilisateur supprimé");
            return true;
        }
        return false;
    }

    @Override
    public User addUser(User user) {
//        User u = userRepo.findById(user.getIdUser()).get();
//        if (u != null) {
//            System.out.println("Ajout effectué");
            return userRepo.save(user);

//        }
//        else{
//            System.out.println("utilisateur existe déja");
//            return null;
//
//        }
    }

    @Override
    public <T> User getUser(T par) {
        if (par instanceof Integer) {
            return userRepo.findById((Integer) par).get();
        }
        if (par instanceof String) {
            return userRepo.findByEmail((String) par);
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<Club> getClubs() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("connectez vous ");
        }
        String username = authentication.getName();
        User user = userRepo.findByName(username);
        return user.getClubs();
    }

    @Override
    public String rejoindreClub(Integer clubId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("connectez vous ");
        }
        String username = authentication.getName();
        User user = userRepo.findByName(username);
        Club club = clubRepo.findById(clubId).get();
        if(user.getClubs().contains(club)){
            // meme page de club mais avec un pop up qui dit ceci
            return "Vous avez deja rejoingné ce club";
        }
        else{
            user.getClubs().add(club);
            return "You have joined the club";
        }
    }

    @Override
    public HashMap<User, Book> reccomenderLivre(String livreId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("connectez vous ");
        }
        String username = authentication.getName();
        User user = userRepo.findByName(username);
        Book book = bookRepo.findById(livreId).get();
        HashMap<User,Book> map = new HashMap<>();
        map.put(user,book);
//        boolean check = user.getRecommendedbooks().contains(book);
//        if(check == false){
//            user.getRecommendedbooks().add(book);
//            return "The book" +
//                    bookRepo.findById(livreId).get().getTitle() +"is added to your reccomendations";
//        }
//        else{
//            return "Vous avez déja recommendé ce livre";
//        }
        return map;

    }

    @Override
    public String ajouterLivreAuPlaylist(String livreId, Integer playlistId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("connectez vous ");
        }
        String username = authentication.getName();
        User user = userRepo.findByName(username);
        Playlist playlist = user.getPlaylits().get(playlistId);
        Book book = bookRepo.findById(livreId).get();
        boolean check = playlist.getBooks().contains(book);
        if(check == false){
            playlist.getBooks().add(book);
            return "The book" +
                    bookRepo.findById(livreId).get().getTitle() +"is added succesfuly";
        }
        else{
           return "Erreur dans l'ajout";
        }
    }

    @Override
    public String creerPlaylist(Playlist playlist) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("connectez vous ");
        }
        String username = authentication.getName();
        User user = userRepo.findByName(username);
        Playlist p = playlistRepo.findById(playlist.getId()).get();
        boolean check=user.getPlaylits().contains(p);
        if(check == false){
            user.getPlaylits().add(playlist);
            return "Playlist crée";
        }
        else{
            return "Playlist déja crée";
        }
    }

    @Override
    public List<Book> searchBook(String search) {
//        List<Book> books = bookRepo.findAll();
//        List<Book> filteredBooks = new ArrayList<>();
//        for (Book book : books) {
//            if (book.getTitle().toLowerCase().contains(search.toLowerCase())) {
//                filteredBooks.add(book);
//            }
//        }
//        return filteredBooks;
        return bookRepo.findByTitleContains(search);
    }

//    @Override
//    public List<Book> similarBooks(String title) throws IOException {
//        ProcessBuilder processBuilder = new ProcessBuilder("python3", "C:/Users/hp/Desktop/PFA/PFA/src/main/resources/ai", title);
//
//        // Start the process
//        Process process = processBuilder.start();
//
//        // Read output from Python script
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        List<String> list;
//        while ((list = Collections.singletonList(reader.readLine())) != null) {
//            List<Book> books = new ArrayList<>();
//            for (String s : list) {
//                books.add(bookRepo.findByTitle(s));
//            }
//            return books;
//        }
//        return null;
//    }

//    @Override
//    public List<Book> searchBook(String search) throws IOException, InterruptedException {
//        ProcessBuilder processBuilder = new ProcessBuilder("python3", "C:/Users/hp/Desktop/PFA/PFA/src/main/resources/ai", search);
//
//        // Start the process
//        Process process = processBuilder.start();
//
//        // Read output from Python script
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        List<String> list;
//        while ((list = Collections.singletonList(reader.readLine())) != null) {
//            List<Book> books = new ArrayList<>();
//            for (String s : list) {
//                books.add(bookRepo.findByTitle(s));
//            }
//            return books;
//        }
//        return null;
//    }


//    @Override
//    public List<Book> getBooks() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            System.out.println("connectez vous ");
//        }
//        String username = authentication.getName();
//        User user = userRepo.findByName(username);
//        return user.getRecommendedbooks();
//    }



    @Override
    public List<Playlist> getPlaylists() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("connectez vous ");
        }
        String username = authentication.getName();
        User user = userRepo.findByName(username);
        return user.getPlaylits();
    }



}
