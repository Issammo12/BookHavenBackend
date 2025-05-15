package org.example.pfa.service;

import org.example.pfa.dao.entities.Club;

import java.util.List;

public interface ClubManager {
    public Club addClub(Club club);
    public Club updateClub(Club club);
    public boolean deleteClub(Integer id);
    public Club getClubById(Integer id);
    public List<Club> getAllClubs();
}
