package com.gol.ants_quests.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.hibernate.services.QuestsHibService;
import com.gol.ants_quests.hibernate.services.StudentsHibService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/studenti")
@RequiredArgsConstructor
public class ControllerStudenti {

    private final QuestsHibService QHS;
    private final StudentsHibService SHS;

    @GetMapping("/")
    public String homepageStudente(HttpSession session,@RequestParam String nome,@RequestParam String cognome) {
        // inserire una lista di questionari per lo studente nella session
        session.setAttribute("listaQuestionari", QHS.findAll());
        // lista questionari uguale per ogni studente
        // se assegnato ad una classe vede anche quelli del corso altrimenti no
        session.setAttribute("studente", SHS.findByNomeAndCognome( nome, cognome));
        return "studenti-home.html";
    }

    @GetMapping("/doQuestionario")
    public String doQuestionario() {
        return "exe-test.html";
    }

}
