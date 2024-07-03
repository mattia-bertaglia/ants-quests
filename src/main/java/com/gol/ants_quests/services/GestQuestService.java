package com.gol.ants_quests.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestQuestService {

   private final CategorieHibService questSrv;

    public void findAll(Model model) {

        model.addAttribute("listaCategorie", questSrv.getAllCategories());
    }




}
