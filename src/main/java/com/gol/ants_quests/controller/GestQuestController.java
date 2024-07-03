package com.gol.ants_quests.controller;

import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.services.GestQuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequiredArgsConstructor
public class GestQuestController {

    private final CategorieHibService categoriaQuestService;
    private final GestQuestService gestQuestService;

        @GetMapping("/nuovaQuest")
        public String showQuestForm(Model model) {
            model.addAttribute("listaCategorie", categoriaQuestService.getAllCategories());
            return "newquest.html";
        }

        @PostMapping("/inserisciTitolo")
        public String inserisciTitolo(@RequestParam String titolo, @RequestParam String categoriaId) {
            CategoriaQuest categoriaQuest = categoriaQuestService.getCategoryById(categoriaId).orElse(null);
            if (categoriaQuest == null) {
            throw new IllegalArgumentException("Invalid category ID");
        }
            gestQuestService.createQuestWithTitolo(titolo);
            return "newquest.html"; 
        }

	    @GetMapping("/categorie")
        public String categorie(Model model) {

            gestQuestService.findAll(model);
            return "newquest.html";
        }
}
