package com.gol.ants_quests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.services.UserHibService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserHibService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public User getUserByUsernameEmail(@PathVariable String email) {
        return userService.getUserByUsernameEmail(email);
    }

    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@PathVariable User.Role role) {
        return userService.getUsersByRole(role);
    }

    @GetMapping("/enabled")
    public List<User> getEnabledUsers() {
        return userService.getEnabledUsers();
    }

    @PutMapping("/enable")
    public int updateUserEnableStatus(@RequestParam String usernameEmail, @RequestParam boolean enable) {
        return userService.updateUserEnableStatus(usernameEmail, enable);
    }
}

