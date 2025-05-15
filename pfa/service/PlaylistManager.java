package org.example.pfa.service;

import org.example.pfa.dao.entities.Playlist;

import java.util.List;

public interface PlaylistManager {
    public List<Playlist> getAllPlaylists();
    public Playlist getPlaylistById(Integer id);
    public Playlist addPlaylist(Playlist playlist);
    public Playlist updatePlaylist(Playlist playlist);
    public boolean deletePlaylist(Integer id);
}
