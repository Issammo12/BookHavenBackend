package org.example.pfa.dao.repositories;

import org.example.pfa.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByName(String name);
    public User findByEmail(String email);

    boolean existsByEmail(String email);
//   public User findById(int id);
//   public User addUser(User user);
//   public User updateUser(User user);
//    public boolean deleteUser(int id);
//    public User getUserById(int id);
//    public List<User> findAll();
//    public List<User> getUsers();
//    public User findUser();


}
