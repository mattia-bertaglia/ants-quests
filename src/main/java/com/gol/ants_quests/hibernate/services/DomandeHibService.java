package com.gol.ants_quests.hibernate.services;

import org.springframework.stereotype.Service;
import com.gol.ants_quests.hibernate.entities.DomandeQuest;
import com.gol.ants_quests.hibernate.repositories.DomandeQuestsRepository;

@Service
public class DomandeHibService extends GenericHibService<DomandeQuest, Integer, DomandeQuestsRepository> {

    public DomandeHibService(DomandeQuestsRepository repository) {
        super(repository);
    }
    
}
