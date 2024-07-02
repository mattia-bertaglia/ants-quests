package com.gol.ants_quests.hibernate.services;

import org.springframework.stereotype.Service;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.repositories.QuestsRepository;




@Service
public class QuestHibService extends GenericHibService<Quest, Integer, QuestsRepository> {
    
    public QuestHibService(QuestsRepository repository) {
        super(repository);
    }





}
