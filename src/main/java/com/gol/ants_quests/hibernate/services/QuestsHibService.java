package com.gol.ants_quests.hibernate.services;

import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.repositories.QuestsRepository;

@Service
public class QuestsHibService extends GenericHibService<Quest, Long, QuestsRepository> {

    public QuestsHibService(QuestsRepository repository) {
        super(repository);
    }

    // completato
}
