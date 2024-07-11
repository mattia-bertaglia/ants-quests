package com.gol.ants_quests.hibernate.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.EsitoQuest;
import com.gol.ants_quests.hibernate.repositories.EsitiQuestsRepository;

@Service
public class EsitiHibService extends GenericHibService<EsitoQuest, Long, EsitiQuestsRepository> {

    public EsitiHibService(EsitiQuestsRepository repository) {
        super(repository);
    }

    public List<EsitoQuest> findData(LocalDate data_inizio, LocalDate data_fine) {
        return getRepository().findByDataEsecuzioneBetween(data_inizio, data_fine);

    }

    public List<EsitoQuest> findByStudente(Long idStudente) {
        return getRepository().findByStudenteIdStudente(idStudente, Sort.by(Direction.DESC, "idEstQst"));
    }
}
