package com.temp.webshop.authentication.service;

import com.temp.webshop.authentication.repository.UserRepository;
import com.temp.webshop.authentication.entity.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ApplicationUser saveUser(ApplicationUser user) {
        return userRepository.save(user);
    }

    public Optional<ApplicationUser> getOneUser(Long id) {
        Optional<ApplicationUser> user = userRepository.findById(id);
        return user;
    }

    public List<ApplicationUser> getAllUsers() {
        return userRepository.findAll();
    }

    public ApplicationUser updateUser(Long id, ApplicationUser user) {
        ApplicationUser newUser = userRepository.findById(id).orElse(null);
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        return newUser;
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }
}