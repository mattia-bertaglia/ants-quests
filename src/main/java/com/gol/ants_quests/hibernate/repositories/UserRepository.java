package com.gol.ants_quests.hibernate.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.gol.ants_quests.hibernate.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    // Metodo per trovare un utente per usernameEmail
    Optional<User> findByUsernameEmail(String usernameEmail);

    // Metodo per trovare tutti gli utenti con un determinato ruolo
    List<User> findByRole(User.Role role);

    // Metodo per trovare tutti gli utenti abilitati
    List<User> findByEnabled(boolean enabled);

    
    List<User> findEnableUsersByRole(@Param("role") User.Role role);
    
}