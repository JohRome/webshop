package com.temp.webshop.authentication.service;

import com.temp.webshop.webshop.entity.User;
import com.temp.webshop.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getOneUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User user) {
        User newUser = userRepository.findById(id).orElse(null);
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        return newUser;
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }
}