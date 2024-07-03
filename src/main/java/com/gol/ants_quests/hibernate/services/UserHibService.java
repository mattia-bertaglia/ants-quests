package com.gol.ants_quests.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.repositories.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserHibService extends GenericHibService<User, Integer, UserRepository> {

    public UserHibService(UserRepository repository){
        super(repository);
    }

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsernameEmail(String usernameEmail) {
        return userRepository.findByUsernameEmail(usernameEmail);
    }

    public List<User> getUsersByRole(User.Role role) {
        return userRepository.findByRole(role);
    }

    public List<User> getEnabledUsers() {
        return userRepository.findByEnabled(true);
    }

    public boolean updateUserEnabledStatus(String usernameEmail, boolean enabled) {
        Optional<User> user = userRepository.findByUsernameEmail(usernameEmail);
        if (user.isPresent()) {
            user.get().setEnabled(enabled);
            userRepository.save(user.get());
            return true;
        } else {
            return false;
        }
    }    
}