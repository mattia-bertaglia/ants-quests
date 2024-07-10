package com.gol.ants_quests.business;


import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestQuestService {
    
    /* 
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

    */

}
