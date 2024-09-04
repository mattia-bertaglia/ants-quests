package com.gol.ants_quests.controllers;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gol.ants_quests.business.AuthService;
import com.gol.ants_quests.business.ErrorService;
import com.gol.ants_quests.business.GestQuestService;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.util.Ruolo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quest")
public class QuestController {

    private final GestQuestService gestSrv;
    private final AuthService authService;
    private final ErrorService errorService;
    private final Ruolo ruolo = Ruolo.admin;

    // done
    @GetMapping("/esiti")
    public String esiti(HttpSession session, @RequestParam HashMap<String, String> params, Model model) {

        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            gestSrv.openEsiti(model);
            return "esitiQuestionari.html";
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruolo)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            return "redirect:/";
        }

    }

    // done
    @GetMapping("/lista")
    public String lista(HttpSession session, Model model) {
        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            gestSrv.openLista(model);
            return "listaQuestionari.html";
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruolo)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            return "redirect:/";
        }

    }

    @PostMapping("/gestione")
    public String gestioneQuest(HttpSession session, @RequestParam("id_quest") String idQuest, Model model) {

        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            gestSrv.openGestione(idQuest, model);
            return "gestioneQuestionario.html";
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruolo)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            return "redirect:/";
        }
    }

    @GetMapping("/gestione")
    public String gestioneQuest() {
        return "redirect:/";
    }

    @PostMapping("/gestionedomande")
    @ResponseBody
    public String gestioneDomande(@RequestBody Quest jsonQuest, HttpSession session) {

        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            return gestSrv.gestioneDomande(jsonQuest);
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruolo)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            return "redirect:/";
        }
    }

    @GetMapping("/gestionedomande")
    public String gestioneDomande() {
        return "redirect:/";
    }

    @PostMapping("/savetest")
    @ResponseBody
    public String saveTest(@RequestParam HashMap<String, String> params, HttpSession session) {

        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            return gestSrv.saveTest(params);
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruolo)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            return "redirect:/";
        }
    }

    @GetMapping("/savetest")
    public String saveTest() {
        return "redirect:/";
    }

    @PostMapping("/attivo")
    public String attivaDisattivaQuest(@RequestParam("idquest") Long idQuest, HttpSession session) {

        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            gestSrv.attivaDisattivaQuest(idQuest);
            return "redirect:/quest/lista";
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruolo)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            return "redirect:/";
        }
    }

    @GetMapping("/attivo")
    public String attivaDisattivaQuest() {
        return "redirect:/";
    }

}
