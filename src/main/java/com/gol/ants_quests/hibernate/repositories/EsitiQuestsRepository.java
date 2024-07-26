package com.gol.ants_quests.hibernate.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gol.ants_quests.hibernate.entities.EsitoQuest;

@Repository
public interface EsitiQuestsRepository extends JpaRepository<EsitoQuest, Long> {

    List<EsitoQuest> findByDataEsecuzioneBetween(LocalDate data_inizio, LocalDate data_fine);

    List<EsitoQuest> findByStudenteIdStudente(Long idStudente, Sort sort);

    @Query(value = "select * from esiti_quests where data_esecuzione > adddate(current_date(), interval -2 week) order by id_est_qst desc", nativeQuery = true)
    List<EsitoQuest> findLastEsiti();

}
