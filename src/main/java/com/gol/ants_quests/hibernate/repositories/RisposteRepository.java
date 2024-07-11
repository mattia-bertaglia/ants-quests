package com.gol.ants_quests.hibernate.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gol.ants_quests.hibernate.entities.RispostaQuest;

@Repository
public interface RisposteRepository extends JpaRepository<RispostaQuest, Long> {

    @Query("SELECT r FROM RispostaQuest r WHERE r.idAns = :idAns AND r.corretta = true")
    Optional<RispostaQuest> findRispostaCorrettaById(@Param("idAns") Long idAns);

}
