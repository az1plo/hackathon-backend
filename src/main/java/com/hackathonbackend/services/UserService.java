package com.hackathonbackend.services;

import com.hackathonbackend.models.dto.UserDTO;
import com.hackathonbackend.models.entity.User;
import com.hackathonbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User findOrCreateUser(String name) {
        return userRepo.findByName(name)
                .orElseGet(() -> {
                    User user = new User();
                    user.setName(name);
                    return userRepo.save(user);
                });
    }

    public User getUser(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDTO getUserDTO(Long id) {
        User user = getUser(id);
        return new UserDTO(
                user.getId(),
                user.getName(),
                null
        );
    }
}
