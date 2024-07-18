package com.gol.ants_quests.business;


import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.services.DomandeHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;
import com.gol.ants_quests.hibernate.services.RisposteHibService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestQuestService {
    
    private final QuestsHibService qstSrv;
    private final DomandeHibService domSrv;
    private final RisposteHibService risSrv;

    public void findDomandeByID(String domanda_id,Model model){
        model.addAttribute("quest", qstSrv.findByID(Long.parseLong(domanda_id)).get());
    }

    public void empyObject(Model model){
        Quest questionario = new Quest();
        questionario.setCategoriequest(new CategoriaQuest());
        model.addAttribute("quest", questionario);

    }

/* 
    public String saveTest(){
        
        CategoriaQuest categoria = new CategoriaQuest();
        String id_domanda = "";

        if(params.get("type") != null && params.get("titolo") != null && params.get("id_quest") == ""){
            Quest questionario = new Quest();
            categoria.setIdCat(params.get("type"));
            questionario.setCategoriequest(categoria);
            questionario.setTitolo(params.get("titolo"));
            qstSrv.save(questionario);
            id_domanda = "" + questionario.getIdQst();
        }else{
            Quest questEsistente = qstSrv.findByID(Long.parseLong(params.get("id_quest"))).get();
            categoria.setIdCat(params.get("type"));
            questEsistente.setCategoriequest(categoria);
            questEsistente.setTitolo(params.get("titolo"));
            qstSrv.save(questEsistente);
            id_domanda = params.get("id_quest");
        }

        return id_domanda;
        
        return "";
        

    }*/




    /* 
    public String modificaDomanda(String jsonOggetto){



    }*/

      /* aggiunte Ross */

      public String saveTest(String jsonOggetto) {
        // Supponiamo che il JSON contenga dati relativi a un oggetto Quest
        // Puoi utilizzare una libreria come Jackson per deserializzare il JSON in un
        // oggetto Java
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Quest quest = objectMapper.readValue(jsonOggetto, Quest.class);

            // Ora puoi salvare l'oggetto Quest o elaborarlo come necessario
            // Esempio di salvataggio
            Quest savedQuest = saveQuest(quest);

            // Ritorna un messaggio di conferma o l'id del nuovo quest salvato
            return String.valueOf(savedQuest.getIdQst());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Gestisci l'errore di parsing del JSON
            return "Errore nel salvataggio del quest";
        }
    }
     // Esempio di metodo per salvare un oggetto Quest (sostituiscilo con la tua
    // logica di salvataggio effettiva)
    private Quest saveQuest(Quest quest) {
        // Qui implementa la logica per salvare l'oggetto Quest nel database o nel
        // repository appropriato
        // Ad esempio, potresti utilizzare un repository Spring Data JPA
        // questRepository.save(quest);
        return quest; // Ritorna l'oggetto Quest salvato (potrebbe includere l'id assegnato dal
                      // database)
    }

    public Quest saveQuest(HashMap<String, String> params) {
        Quest quest = new Quest();

        if (params.get("idquest") != null && !"".equals(params.get("idquest")))
            quest.setIdQst(Long.parseLong(params.get("idquest")));
        quest.setTitolo(params.get("titolo"));

    
        return qstSrv.save(quest);
    }

    /* 
    public CategoriaQuest saveCategoria(HashMap<String, String> params) {
        CategoriaQuest categoriaQuest = new CategoriaQuest();
    }
        */
}
