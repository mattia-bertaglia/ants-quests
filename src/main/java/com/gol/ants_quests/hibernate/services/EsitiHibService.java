package com.gol.ants_quests.hibernate.services;


import org.springframework.stereotype.Service;
import com.gol.ants_quests.hibernate.entities.EsitoQuest;
import com.gol.ants_quests.hibernate.repositories.EsitiQuestsRepository;

@Service
public class EsitiHibService extends GenericHibService<EsitoQuest, Long, EsitiQuestsRepository> {

    public EsitiHibService(EsitiQuestsRepository repository) {
        super(repository);
    }
}
