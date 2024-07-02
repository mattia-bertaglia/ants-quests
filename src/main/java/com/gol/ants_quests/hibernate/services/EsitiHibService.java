package com.gol.ants_quests.hibernate.services;

import org.springframework.stereotype.Service;
import com.gol.ants_quests.hibernate.entities.EsitiQuest;
import com.gol.ants_quests.hibernate.repositories.EsitiQuestsReposity;

@Service
public class EsitiHibService extends GenericHibService<EsitiQuest, Integer, EsitiQuestsReposity> {

    public EsitiHibService(EsitiQuestsReposity repository) {
        super(repository);
    }
    
}
