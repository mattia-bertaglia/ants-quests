package com.gol.ants_quests.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gol.ants_quests.services.GestQuestService;

import lombok.RequiredArgsConstructor;




@Controller
@RequiredArgsConstructor
public class GestQuestController {

   private final GestQuestService CatServ;
   

    @GetMapping("/categorie")
    public String categorie(Model model) {

        CatServ.findAll(model);
        return "newquest.html";
    }
       
}
