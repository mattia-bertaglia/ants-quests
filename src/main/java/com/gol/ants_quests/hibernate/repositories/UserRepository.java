package com.gol.ants_quests.hibernate.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gol.ants_quests.hibernate.entities.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Metodo per trovare un utente per usernameEmail
    Optional<User> findByUsernameEmail(String usernameEmail);

    // Metodo per trovare tutti gli utenti con un determinato ruolo
    List<User> findByRole(User.Role role);

    // Metodo per trovare tutti gli utenti abilitati
    List<User> findByEnable(boolean enable);

    @Query("SELECT u FROM User u WHERE u.role = :role AND u.enable = true")
    List<User> findEnabledUsersByRole(@Param("role") User.Role role);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.enable = :enable WHERE u.usernameEmail = :usernameEmail")
    int updateUserEnableStatus(@Param("usernameEmail") String usernameEmail, @Param("enable") boolean enable);

    
}
