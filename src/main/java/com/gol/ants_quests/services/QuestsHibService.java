package com.gol.ants_quests.services;

import org.springframework.stereotype.Service;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.repositories.QuestsRepository;

@Service
public class QuestsHibService extends GenericHibService<Quest, Integer, QuestsRepository> {

    public QuestsHibService(QuestsRepository repository) {
        super(repository);
    }

}
