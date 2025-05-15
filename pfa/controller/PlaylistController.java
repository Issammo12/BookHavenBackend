package org.example.pfa.controller;

import org.example.pfa.dao.entities.Playlist;
import org.example.pfa.service.PlaylistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    PlaylistService playlistService;
    @GetMapping("/ListPlaylists")
    public List<Playlist> AllPlaylists() {
        List<Playlist> playlists = playlistService.getAllPlaylists();
        return playlists;
    }

    @GetMapping("/getPlaylist/{id}")
    public Playlist GetPlaylist(@RequestParam int id) {
        return playlistService.getPlaylistById(id);
    }

    @PostMapping("/addPlaylist")
    public String AddPlaylist(Playlist playlist) {
        Playlist playlist1 = playlistService.addPlaylist(playlist);
        if (playlist1 != null) {
            return "Playlist added successfully";
        }
        return "error";
    }

    @PutMapping("/updatePlaylist")
    public String UpdatePlaylist(@RequestBody Playlist playlist) {
        Playlist playlist1 = playlistService.updatePlaylist(playlist);
        if (playlist1 != null) {
            return "Playlist updated successfully";
        }
        return "error";
    }

    @DeleteMapping("/deletePlaylist/{id}")
    public String DeletePlaylist(@PathVariable Integer playlistId) {
        boolean result = playlistService.deletePlaylist(playlistId);
        if(result) {
            return "Playlist deleted successfully";
        }
        else {
            return "ERROR";
        }
    }


}
