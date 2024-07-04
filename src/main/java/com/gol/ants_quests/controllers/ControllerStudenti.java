package com.gol.ants_quests.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.hibernate.services.StudentsHibService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/studenti")
@RequiredArgsConstructor
public class ControllerStudenti {

    private final CategorieHibService CHS;
    private final StudentsHibService SHS;
    

    @PostMapping("/")
    public String homepageStudente(HttpSession session, @RequestParam String nome, @RequestParam String cognome) {
        // inserire una lista di questionari per lo studente nella session

        List<CategoriaQuest> categorie = CHS.findAll();
        session.setAttribute("categorie", categorie);
        // lista questionari uguale per ogni studente
        // se assegnato ad una classe vede anche quelli del corso altrimenti no
        session.setAttribute("studente", SHS.findByNomeAndCognome(nome, cognome));
        return "studenti-home.html";
    }

    @GetMapping("/doQuestionario")
    public String doQuestionario() {
        return "exe-test.html";
    }

}
