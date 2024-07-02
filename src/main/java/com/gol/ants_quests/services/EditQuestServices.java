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

    public Optional<Quest> updateQuest(Integer id, Quest questDetails) {
        Optional<Quest> quest = questService.findByID(id);
        if (quest.isPresent()) {
            Quest questToUpdate = quest.get();
            questToUpdate.setCategoriaId(questDetails.getCategoriaId());
            questToUpdate.setTitolo(questDetails.getTitolo());
            return Optional.of(questService.save(questToUpdate));
        } else {
            return Optional.empty();
        }
    }

    public void deleteQuest(Integer id) {
        questService.delete(id);
    }
}
