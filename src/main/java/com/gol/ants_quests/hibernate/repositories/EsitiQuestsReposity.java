package com.gol.ants_quests.hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gol.ants_quests.hibernate.entities.EsitiQuest;

@Repository
public interface EsitiQuestsReposity extends JpaRepository<EsitiQuest, Integer>{

}
