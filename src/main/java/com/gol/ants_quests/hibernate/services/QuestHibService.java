package com.gol.ants_quests.hibernate.services;

import org.springframework.stereotype.Service;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.repositories.QuestRepository;




@Service
public class QuestHibService extends GenericHibService<Quest, Integer, QuestRepository> {
    
    public QuestHibService(QuestRepository repository) {
        super(repository);
    }





}
