package com.gol.ants_quests.controller;

import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.repositories.UserRepository;
import com.gol.ants_quests.util.Ruolo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam String usernameEmail,
            @RequestParam String passkey) {
        try {
            // Crea un nuovo utente
            User newUser = new User();
            newUser.setUsernameEmail(usernameEmail);
            newUser.setPasskey(passkey);
            newUser.setRuolo(Ruolo.guest); // Imposta il ruolo a GUEST per i nuovi utenti

            // Salva l'utente nel database
            User savedUser = userRepository.save(newUser);

            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore durante il salvataggio dell'utente.");
        }
    }
}
