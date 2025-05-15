package org.example.pfa.controller;

import org.example.pfa.dao.entities.Club;
import org.example.pfa.service.ClubService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/clubs")
public class ClubController {
    ClubService clubService;
    @PostMapping("/addClub")
    public String addClub(@RequestBody Club club) {
        clubService.addClub(club);
        return "AddClub";
    }

    @GetMapping("/allClubs")
    public List<Club> getAllClubs() {
        List<Club> clubs = clubService.getAllClubs();
        return clubs;
    }

    @GetMapping("/getClub/{id}")
    public Club getClubById(@PathVariable int id) {
        Club club = clubService.getClubById(id);
        return club;
    }

    @PutMapping("/updateClub")
    public String updateClub(@RequestBody Club club) {
        Club clubUpdated = clubService.updateClub(club);
        return clubUpdated.getName() + " has been updated";
    }

    @DeleteMapping("/deleteClub/{id}")
    public String deleteClub(@PathVariable int id) {
        boolean c=clubService.deleteClub(id);
        if (c==true) {
            return clubService.getClubById(id).getName() + " has been deleted";
        }
        return "Delete failed";
    }

}
