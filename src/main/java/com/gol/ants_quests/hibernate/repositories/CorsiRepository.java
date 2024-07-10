
package com.gol.ants_quests.hibernate.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gol.ants_quests.hibernate.entities.Corso;

@Repository

public interface CorsiRepository extends JpaRepository<Corso, Long> {

    List<Corso> findByNome(String nome);

    List<Corso> findByDataInizioOrDataFine(Date dataInizio, Date dataFine);

    List<Corso> findAll(Sort sort);

}
