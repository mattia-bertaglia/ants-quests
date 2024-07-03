package com.gol.ants_quests.controller;

import com.gol.ants_quests.hibernate.services.CategorieHibService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
@RequiredArgsConstructor
public class GestQuestController {

   private final CategorieHibService CatServ;
   

    @GetMapping("/categorie")
    public String categorie(Model model) {

        CatServ.findAll();
        return "newquest.html";
    }
       
}
