package com.gol.ants_quests.hibernate.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.repositories.UsersRepository;

@Service
public class UsersHibService extends GenericHibService<User, Long, UsersRepository> {

    public UsersHibService(UsersRepository userRepository) {
        super(userRepository);
    }

    public User findByUsernameEmail(String usernameEmail) {
        Optional<User> userOptional = getRepository().findByUsernameEmail(usernameEmail);
        return userOptional.orElse(null);
    }

    public boolean userExists(String usernameEmail) {
        return getRepository().existsByUsernameEmail(usernameEmail);
    }
}
