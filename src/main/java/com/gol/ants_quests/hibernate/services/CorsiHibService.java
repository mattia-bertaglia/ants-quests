
package com.gol.ants_quests.hibernate.services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.Corso;
import com.gol.ants_quests.hibernate.repositories.CorsiRepository;

@Service
public class CorsiHibService extends GenericHibService<Corso, Integer, CorsiRepository> {
    public CorsiHibService(CorsiRepository repository) {
        super(repository);
    }

    public List<Corso> findByNome(String nome) {
        return getRepository().findByNome(nome);
    }

    List<Corso> findByDataInizioOrDataFine(Date dataInizio, Date dataFine) {
        return getRepository().findByDataInizioOrDataFine(dataInizio, dataFine);
    }

}
