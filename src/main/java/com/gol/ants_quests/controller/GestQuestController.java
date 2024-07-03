package com.gol.ants_quests.controller;


import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GestQuestController {

    CategorieHibService categoriaQuestService;
    QuestsHibService questService;

    @GetMapping
    public List<CategoriaQuest> getAllCategories() {
      
        return categoriaQuestService.getAllCategories();
    }

    @GetMapping("/questID")
    public Quest getQuestById(@PathVariable Integer id) {
       
        return questService.getQuestById(id).orElse(null);
    }

    @PostMapping
    public Quest createQuest(@RequestBody Quest quest) {
        return questService.createQuest(quest);
    }

    @PostMapping("/NuovaQuest")
    public Quest createQuest(@RequestParam String titolo, @RequestParam String categoriaId) {
        CategoriaQuest categoriaQuest = categoriaQuestService.getCategoryById(categoriaId).orElse(null);
        if (categoriaQuest == null) {
            throw new IllegalArgumentException("Invalid category ID");
        }
        return questService.createQuest(titolo, categoriaQuest);
    }

    @PutMapping("/updateQuest")
    public Quest updateQuest(@PathVariable Integer id, @RequestBody Quest quest) {
        quest.setIdQst(id);
        return questService.updateQuest(quest);
    }

    @DeleteMapping("/cancellaQuest")
    public void deleteQuest(@PathVariable Integer id) {
        questService.deleteQuest(id);
    }
}
