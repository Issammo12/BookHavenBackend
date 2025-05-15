package org.example.pfa.service;

import org.example.pfa.dao.entities.Playlist;
import org.example.pfa.dao.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlaylistService implements PlaylistManager{
    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    public Playlist getPlaylistById(Integer id) {
        return playlistRepository.findById(id).get();
    }

    @Override
    public Playlist addPlaylist(Playlist playlist) {
        Playlist p=playlistRepository.findById(playlist.getId()).get();
        if (p==null) {
            System.out.println("Playlist added");
            return playlistRepository.save(playlist);
        }
        else return null;
    }

    @Override
    public Playlist updatePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public boolean deletePlaylist(Integer id) {
        Playlist p=playlistRepository.findById(id).get();
        if (p!=null) {
            playlistRepository.delete(p);
            System.out.println("Playlist deleted");
            return true;
        }
        else return false;
    }
}
