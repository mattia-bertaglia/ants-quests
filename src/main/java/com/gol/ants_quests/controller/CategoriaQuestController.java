package com.gol.ants_quests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoriaQuestController {

    @Autowired
    private CategorieHibService categoriaQuestService;

    @GetMapping
    public List<CategoriaQuest> getAllCategories() {
        return categoriaQuestService.getAllCategories();
    }
}
