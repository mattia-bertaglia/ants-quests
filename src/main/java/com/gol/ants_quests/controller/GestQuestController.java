package com.gol.ants_quests.controller;

import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.services.GestQuestService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




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
