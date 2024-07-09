package com.gol.ants_quests.controllers;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.business.ExeQuestService;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.services.QuestsHibService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/homeStud")
@RequiredArgsConstructor
public class HomeStudentiController {

    private final QuestsHibService QHS;

    private final ExeQuestService questService;

    @GetMapping("/")
    public String homepageStudente(HttpSession session, Model model) {
        // inserire una lista di questionari per lo studente nella session
        log.info("Apertura Home Page Studente ...");

        User user = (User) session.getAttribute("user");
        questService.openHomeStud(model, user.getStudente().getIdStudente());

        log.info("Apertura Home Page Studente ... DONE");
        return "homeStud.html";
    }

    @GetMapping("/doQuestionario")
    public String doQuestionario(HttpSession session, Model model, @RequestParam("mySelect") Long selectedValue) {

        Optional<Quest> quest = QHS.findByID(selectedValue);

        model.addAttribute("quest", quest.get());

        return "exe-test.html";

    }

    @PostMapping("/submit-answers")
    public String submitAnswers(HttpSession session, @RequestParam HashMap<String, String> params) {

        User user = (User) session.getAttribute("user");
        questService.eleborazioneQuestionario(user, params);

        return "redirect:/homeStud/";
    }

}
