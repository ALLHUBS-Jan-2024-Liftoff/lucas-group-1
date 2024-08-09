package org.codestars.tenttalk_api.controllers;

import org.codestars.tenttalk_api.models.User;
import org.codestars.tenttalk_api.models.data.UserRepository;
import org.codestars.tenttalk_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return user;
        } else {
            return null;  // Failed login
        }
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {return userRepository.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        User user1 = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());

        return userRepository.save(user1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id, @RequestBody User user) {
        User user1 = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user1);
        return ResponseEntity.noContent().build();
    }
}

