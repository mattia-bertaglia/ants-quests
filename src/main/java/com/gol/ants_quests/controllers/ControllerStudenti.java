package com.gol.ants_quests.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;
import com.gol.ants_quests.hibernate.services.StudentsHibService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
@RequestMapping("/studenti")
@RequiredArgsConstructor
public class ControllerStudenti {

    private final QuestsHibService QHS;
    private final CategorieHibService CHS;
    private final StudentsHibService SHS;

    @GetMapping("/")
    public String homepageStudente(HttpSession session, @RequestParam String nome, @RequestParam String cognome) {
        // inserire una lista di questionari per lo studente nella session

        List<CategoriaQuest> categorie = CHS.findAll();
        session.setAttribute("categorie", categorie);
        
        log.info("Aggiungo lista di categorie alla sessione");
        // lista questionari uguale per ogni studente
        // se assegnato ad una classe vede anche quelli del corso altrimenti no
        session.setAttribute("studente", SHS.findByNomeAndCognome(nome, cognome));
        return "studenti-home";
    }

    @GetMapping("/doQuestionario")
    public String doQuestionario(HttpSession session,@RequestParam("mySelect") Integer selectedValue) {


       Optional<Quest> quest = QHS.findByID(selectedValue);
        

       session.setAttribute("quest", quest.get());

        return "exe-test.html";

    }

    @PostMapping("/submit-answers")
    public String submitAnswers(HttpSession session) {
       
       Quest corrente = (Quest)session.getAttribute("quest");
        
        return "studenti-home";
    }
    

    

}
