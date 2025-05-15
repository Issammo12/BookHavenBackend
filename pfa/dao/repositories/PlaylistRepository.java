package org.example.pfa.dao.repositories;

import org.example.pfa.dao.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
//    Playlist findByName(String name);
}
