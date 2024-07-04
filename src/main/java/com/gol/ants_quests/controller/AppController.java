package com.gol.ants_quests.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.services.ErrorService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AppController {

    private ErrorService errorServ;

    @GetMapping("/")
    public String openIndex(Model model, HttpSession session, String code) {
         
        
        /*
         * controllo se nella sessione è presente un codice di errore
         * 
         * se è presente richiamo errorService
         */
        if(code == null){
            code = "";
        } else if (!code.isEmpty()) {
            code = "";
        }

        errorServ.getToast(session, code);
        return "index.html";
    }

    @GetMapping("/homeStudente")
    public String homeStud(Model model) {

        return "homeStud.html";
    }

    @GetMapping("/homeAdmin")
    public String homeAdmin(Model model) {

        return "homeAdmin.html";
    }

}
