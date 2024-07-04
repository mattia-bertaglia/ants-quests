package com.gol.ants_quests.services;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    
    // Metodo per verificare se l'utente è loggato
    public boolean isUserLoggedIn(HttpSession session) {
        Boolean isLoggedIn = (Boolean) session.getAttribute("usrlog");
        return isLoggedIn != null && isLoggedIn;
    }

}
