package com.gol.ants_quests.hibernate.services;

import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.repositories.QuestsRepository;

import jakarta.transaction.Transactional;

@Service
public class QuestsHibService extends GenericHibService<Quest, Long, QuestsRepository> {

    public QuestsHibService(QuestsRepository repository) {
        super(repository);
    }

    @Transactional
    public void attivaDisattivaQuest(Long idQuest) {
        getRepository().attivaDisattivaQuest(idQuest);
    }

    // completato
}
