package org.example.userservice.Service;

import org.example.userservice.Model.User;
import org.example.userservice.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public Optional<User> findById(Long id) {
       return userRepo.findById(id);
    }
    public User save(User user) {
        return userRepo.save(user);
    }

}
