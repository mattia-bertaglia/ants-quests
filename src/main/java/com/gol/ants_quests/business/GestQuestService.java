package com.gol.ants_quests.business;


import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.entities.DomandaQuest;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.entities.RispostaQuest;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.hibernate.services.DomandeHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;
import com.gol.ants_quests.hibernate.services.RisposteHibService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestQuestService {
    
    private final CategorieHibService categorieHibService;
    private final QuestsHibService questsHibService;
    private final DomandeHibService domandeHibService;
    private final RisposteHibService rispostaHibService;
    public void findAll(Model model) {

        model.addAttribute("listaCategorie", questsHibService.findAll());
    }

    
    public Quest createQuestWithDomandeERisposte(String titolo, String categoriaId) throws Exception {
        // Fetch the category
        CategoriaQuest categoriaQuest = categorieHibService.findByID(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

       
        Quest quest = new Quest();
        quest.setTitolo(titolo);
        quest.setCategoriequest(categoriaQuest);
        quest = questsHibService.save(quest);
        DomandaQuest domanda = new DomandaQuest();
        domanda = domandeHibService.save(domanda);
        RispostaQuest risposta = new RispostaQuest();
        risposta = rispostaHibService.save(risposta);
                   

        return quest;
    }

}
