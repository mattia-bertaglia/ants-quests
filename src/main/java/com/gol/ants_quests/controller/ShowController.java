package com.gol.ants_quests.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.gol.ants_quests.services.ShowService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ShowController {
    
    private final ShowService showSrv;

    @GetMapping("/esiti")
    public String esiti(Model model, HttpSession session) {

    
        showSrv.findAllEsitiQuest(model);

        return "esitiQuestionari.html";
    }



    @GetMapping("/gestione")
    public String gestione(Model model){

        showSrv.findAllQuestByCategoria(model);
        return "gestioneQuestionari.html";
    }

}
