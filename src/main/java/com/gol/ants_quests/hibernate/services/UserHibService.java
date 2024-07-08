package com.gol.ants_quests.hibernate.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gol.ants_quests.business.ErrorService;
import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.repositories.UserRepository;

@Service
public class UserHibService extends GenericHibService<User, Integer, UserRepository> {

    // RIMANE SOLO super(userRepository)
    public UserHibService(UserRepository userRepository, ErrorService errorService) {
        super(userRepository);
    }

    public User findByUsernameEmail(String usernameEmail) {
        Optional<User> userOptional = getRepository().findByUsernameEmail(usernameEmail);
        return userOptional.orElse(null);
    }

}
