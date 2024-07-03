package com.gol.ants_quests.hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gol.ants_quests.hibernate.entities.EsitoQuest;
import java.util.List;
import java.time.LocalDate;



@Repository
public interface EsitiQuestsRepository extends JpaRepository<EsitoQuest, Integer> {

    List<EsitoQuest> findByDataEsecuzioneBetween(LocalDate data_inizio,LocalDate data_fine);
}
