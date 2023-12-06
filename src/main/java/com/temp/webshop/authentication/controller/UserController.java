package com.temp.webshop.authentication.controller;

import com.temp.webshop.authentication.entity.ApplicationUser;
import com.temp.webshop.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //Lägg nedan i AuthenticationController med instans av UserService
    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> addUser(ApplicationUser user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ApplicationUser>> getOneUser(
            @PathVariable Long id) {
        return ResponseEntity.ok(userService.getOneUser(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<ApplicationUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/")
    public ResponseEntity<ApplicationUser> updateUser(
            @PathVariable Long id,
            @RequestBody ApplicationUser user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
