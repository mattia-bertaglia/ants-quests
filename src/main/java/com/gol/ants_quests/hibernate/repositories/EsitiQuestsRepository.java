package com.gol.ants_quests.hibernate.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gol.ants_quests.hibernate.entities.EsitoQuest;

@Repository
public interface EsitiQuestsRepository extends JpaRepository<EsitoQuest, Long> {

    List<EsitoQuest> findByDataEsecuzioneBetween(LocalDate data_inizio, LocalDate data_fine);

}
