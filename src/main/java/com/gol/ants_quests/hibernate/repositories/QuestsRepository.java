package com.gol.ants_quests.hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gol.ants_quests.hibernate.entities.Quest;

@Repository
public interface QuestsRepository extends JpaRepository<Quest, Long> {

    @Modifying
    @Query(value = "update quests set attivo = !attivo where id_qst = :idQuest", nativeQuery = true)
    void attivaDisattivaQuest(Long idQuest);

}
