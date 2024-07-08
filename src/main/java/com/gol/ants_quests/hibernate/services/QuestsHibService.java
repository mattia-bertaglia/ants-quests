package com.gol.ants_quests.hibernate.services;

import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.repositories.QuestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestsHibService extends GenericHibService<Quest, Integer, QuestsRepository> {
    
    public QuestsHibService(QuestsRepository repository) {
        super(repository);
    }

    @Autowired
    private QuestsRepository questRepository;

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public Optional<Quest> getQuestById(Integer id) {
        return questRepository.findById(id);
    }

    public Quest createQuest(Quest quest) {
        return questRepository.save(quest);
    }


    public Quest updateQuest(Quest quest) {
        return questRepository.save(quest);
    }

    public void deleteQuest(Integer id) {
        questRepository.deleteById(id);
    }

    public Quest createQuest(String titolo, CategoriaQuest categoriaQuest) {
        Quest quest = new Quest();
        quest.setTitolo(titolo);
        quest.setCategoriequest(categoriaQuest);
        return questRepository.save(quest);
    }

    





}
