package com.gol.ants_quests.business;


import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.services.QuestsHibService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestQuestService {
    
    private final QuestsHibService qstSrv;

    public void findDomandeByID(String domanda_id,Model model){
        model.addAttribute("quest", qstSrv.findByID(Long.parseLong(domanda_id)).get());
    }

    public void empyObject(Model model){
        model.addAttribute("quest", new Quest());
    }

}
