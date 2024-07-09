package com.gol.ants_quests.hibernate.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.util.Ruolo;

public interface UsersRepository extends JpaRepository<User, Long> {
    // Metodo per trovare un utente per usernameEmail
    Optional<User> findByUsernameEmail(String usernameEmail);

    // Metodo per trovare tutti gli utenti con un determinato ruolo
    List<User> findByRuolo(Ruolo ruolo);

    Ruolo findRuoloById(Long userId);

    Ruolo findRuoloByUsernameEmail(String usernameEmail);

    List<User> findEnableUsersByRuolo(@Param("ruolo") Ruolo ruolo);

}
