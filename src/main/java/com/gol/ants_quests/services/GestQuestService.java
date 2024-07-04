package com.gol.ants_quests.services;


import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestQuestService {

    private final CategorieHibService questSrv;
    private final QuestsHibService queSrv;

    public void findAll(Model model) {

        model.addAttribute("listaCategorie", questSrv.getAllCategories());
    }


    public Quest createQuestWithTitolo(String titolo, String categoriaId) {
       Optional<CategoriaQuest> categoriaQuestOptional = questSrv.getCategoryById(categoriaId);
     

        CategoriaQuest categoriaQuest = categoriaQuestOptional.get();

        Quest newQuest = new Quest();
        newQuest.setTitolo(titolo);
        newQuest.setCategoriequest(categoriaQuest);
        return queSrv.createQuest(newQuest);
    }

}
