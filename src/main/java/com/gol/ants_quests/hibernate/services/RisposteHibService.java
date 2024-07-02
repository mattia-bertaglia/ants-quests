package com.gol.ants_quests.hibernate.services;

import org.springframework.stereotype.Service;
import com.gol.ants_quests.hibernate.entities.RisposteQuest;
import com.gol.ants_quests.hibernate.repositories.RisposteQuestsRepository;

@Service
public class RisposteHibService extends GenericHibService<RisposteQuest, Integer, RisposteQuestsRepository>{

    public RisposteHibService(RisposteQuestsRepository repository) {
        super(repository);
    }
    
}
