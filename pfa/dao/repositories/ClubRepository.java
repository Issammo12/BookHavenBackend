package org.example.pfa.dao.repositories;

import org.example.pfa.dao.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Integer> {

//    Club findByName(String name);
}
