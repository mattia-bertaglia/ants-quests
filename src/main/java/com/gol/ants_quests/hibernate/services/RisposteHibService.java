package com.gol.ants_quests.hibernate.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.RispostaQuest;
import com.gol.ants_quests.hibernate.repositories.RisposteRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RisposteHibService extends GenericHibService<RispostaQuest, Long, RisposteRepository> {

    public RisposteHibService(RisposteRepository repository) {
        super(repository);
    }

    public Optional<RispostaQuest> findRispostaCorrettaById(Long idAns) {
        log.info("findRispostaCorrettaById=" + idAns);
        return getRepository().findRispostaCorrettaById(idAns);

    }
}
