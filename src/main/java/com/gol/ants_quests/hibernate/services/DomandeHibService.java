package com.gol.ants_quests.hibernate.services;

import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.DomandaQuest;
import com.gol.ants_quests.hibernate.repositories.DomandeRepository;

@Service
public class DomandeHibService extends GenericHibService<DomandaQuest, Integer, DomandeRepository> {

    public DomandeHibService(DomandeRepository repository) {
        super(repository);
    }
}
