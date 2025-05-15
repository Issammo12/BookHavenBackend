package org.example.pfa.controller;

import jakarta.servlet.http.HttpSession;
import org.example.pfa.config.JwtUtil;
import org.example.pfa.dao.entities.Book;
import org.example.pfa.dao.entities.Club;
import org.example.pfa.dao.entities.Playlist;
import org.example.pfa.dao.entities.User;
import org.example.pfa.dao.repositories.UserRepository;
import org.example.pfa.dto.LoginRequest;
import org.example.pfa.dto.LoginResponse;
import org.example.pfa.dto.RegisterRequest;
import org.example.pfa.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.Authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.remote.JMXAuthenticator;
import javax.management.remote.JMXConnectorProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/users")

//You need to add the login and register ,the Search method , similar books ... using the ia algo

public class UserController {
    @Autowired
    private UserManager userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();




    @GetMapping("/userList")
    public List<User> userList() {
        List<User> users = userService.getUsers();
        return users;
    }

    @PostMapping("/userAdd")
    public String userAdd(@RequestParam(name = "id" , defaultValue = "") Integer id ,@RequestParam(name = "name") String name, @RequestParam(name = "email") String email,@RequestParam(name = "password") String password, Model model) {
        User user = new User();
        user.setIdUser(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userService.addUser(user);
        return "user added successfully";
    }



    @DeleteMapping("/userDelete")
    public String userDelete(@RequestParam(name = "id") Integer id) {
        boolean result = userService.deleteUser(id);
        if(result) {
            return "User deleted successfully";
        }
        return "error";
    }

    @PutMapping("/userUpdate")
    public String userUpdate(@RequestParam(name = "id") Integer id ,@RequestParam(name = "name") String name, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        User user = userService.getUser(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userService.updateUser(user);
        return "User updated successfully";
    }


//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user,HttpSession session) {
//
//
//
//        String email = user.getEmail();
//        String password = user.getPassword();
//        if (userService.login(email, password)) {
//            User u = userRepository.findByEmail(email);
//            session.setAttribute("user", u);
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return null;
//        }
//
//
//
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email not found");
        }

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!passwordMatches) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        String token = jwtUtil.generateToken(user);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user); // You might want to send a DTO instead

        return ResponseEntity.ok(response);
    }



//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody User user , HttpSession session) {
////        User user = new User();
////        user.setName(name);
////        user.setEmail(email);
////        user.setPassword(password);
//        user.setIdUser(null);
//        userService.addUser(user);
//        session.setAttribute("user", user);
//        return ResponseEntity.ok("Login successful");
//    }

    @GetMapping("/myClubs")
    public List<Club> MyClubs(Model model) {
        List<Club> clubs=userService.getClubs();
        return clubs;
    }

    @GetMapping("/myPlaylists")
    public List<Playlist> MyPlaylists() {
        List<Playlist> playlists=userService.getPlaylists();
        return playlists;
    }

    @PostMapping("/joinClub")
    public String RejoindreClub(@PathVariable Integer id ) {
        return userService.rejoindreClub(id);

    }

    @PostMapping("/recommendBook")
    public HashMap<User, Book> NoterLivre(@PathVariable String isbn) {
        return userService.reccomenderLivre(isbn);
    }

    @PostMapping("/addBookToPlaylist")
    public String ajouterLivreAuPlaylist(@PathVariable Integer idPlaylist , @PathVariable String isbn10) {
        return userService.ajouterLivreAuPlaylist(isbn10, idPlaylist);

    }

    @PostMapping("/createPlaylist")
    public String CreerPlaylist(@RequestBody Playlist playlist) {
        return userService.creerPlaylist(playlist);
    }

//    @GetMapping("/recommendedBooks")
//    public List<Book> RecommendedBooks(Model model) {
//        List<Book> books=userService.getBooks();
//        return books;
//    }

    @PostMapping("/searchResults/{search}")
    public List<Book> searchResults(@PathVariable String search) {
        return userService.searchBook(search);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getAuthenticatedUser(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7); // remove "Bearer "

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }

        String email = jwtUtil.extractEmail(token);
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.email)) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        User user = new User();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));
        userRepository.save(user);

        String token = jwtUtil.generateToken(user);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);

        return ResponseEntity.ok(response);
    }




}
