package com.gol.ants_quests.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.services.QuestHibService;

import java.util.List;
import java.util.Optional;

@Service
public class EditQuestServices {

    @Autowired
    private QuestHibService questService;

    public List<Quest> getAllQuests() {
        return questService.findAll();
    }

    public Optional<Quest> getQuestById(Integer id) {
        return questService.findByID(id);
    }

    public List<Quest> getQuestsByCategoriaId(String categoriaId) {
        return questService.findByCategoriaId(categoriaId);
    }

    public Quest createQuest(Quest quest) {
        return questService.save(quest);
    }



    public void deleteQuest(Integer id) {
        questService.delete(id);
    }
}
