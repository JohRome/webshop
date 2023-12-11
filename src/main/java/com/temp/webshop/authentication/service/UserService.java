package com.temp.webshop.authentication.service;

import com.temp.webshop.authentication.repository.UserRepository;
import com.temp.webshop.authentication.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Customer saveUser(Customer user) {
        return userRepository.save(user);
    }

    public Optional<Customer> getOneUser(Long id) {
        Optional<Customer> user = userRepository.findById(id);
        return user;
    }

    public List<Customer> getAllUsers() {
        return userRepository.findAll();
    }

    public Customer updateUser(Long id, Customer user) {
        Customer newUser = userRepository.findById(id).orElse(null);
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        return newUser;
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }
}