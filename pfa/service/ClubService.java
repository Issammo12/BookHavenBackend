package org.example.pfa.service;

import org.example.pfa.dao.entities.Club;
import org.example.pfa.dao.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClubService implements ClubManager{
    @Autowired
    private ClubRepository clubRepository;
    @Override
    public Club addClub(Club club) {
        Club c=clubRepository.findById(club.getId()).get();
        if (c==null){
            System.out.println("Club ajouté");
            return clubRepository.save(club);
        }
        return null;
    }

    @Override
    public Club updateClub(Club club) {
        return clubRepository.save(club);
    }

    @Override
    public boolean deleteClub(Integer id) {
        Club c=clubRepository.findById(id).get();
        if (c!=null){
            clubRepository.delete(c);
            System.out.println("Club deleted");
            return true;
        }
        return false;
    }

    @Override
    public Club getClubById(Integer id) {
        return clubRepository.findById(id).get();
    }

    @Override
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }
}
