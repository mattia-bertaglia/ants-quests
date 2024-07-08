package com.gol.ants_quests.business;


import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.entities.DomandaQuest;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.hibernate.services.DomandeHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestQuestService {
    
    private final CategorieHibService categorieHibService;
    private final QuestsHibService questsHibService;
    private final DomandeHibService domandeHibService;
    public void findAll(Model model) {

        model.addAttribute("listaCategorie", questsHibService.findAll());
    }

    
    public Quest createQuestWithDomandeERisposte(String titolo, String categoriaId, List<Map<String, Object>> domandeData) throws Exception {
        // Fetch the category
        CategoriaQuest categoriaQuest = categorieHibService.findByID(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        // Create the quest
        Quest quest = new Quest();
        quest.setTitolo(titolo);
        quest.setCategoriequest(categoriaQuest);
        quest = questsHibService.save(quest);
        

        // Create the questions and answers
        for (Map<String, Object> domandaData : domandeData) {
            DomandaQuest domanda = new DomandaQuest();
            domanda.setDomanda((String) domandaData.get("domanda"));
            domanda.setDom(quest);
            domanda = domandeHibService.save(domanda);

 
        }

        return quest;
    }

}
