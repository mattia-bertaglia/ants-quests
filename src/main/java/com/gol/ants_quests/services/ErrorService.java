package com.gol.ants_quests.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

@Service
public class ErrorService {

    public void getToast(HttpSession session, String message) {
        // Implementazione per HttpSession
        session.setAttribute("toastMessage", message);
    }

    public void getToast(Model model, String message) {
        // Implementazione per Model
        model.addAttribute("toastMessage", message);
    }
}
