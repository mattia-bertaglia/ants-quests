package com.gol.ants_quests.hibernate.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gol.ants_quests.hibernate.entities.Quest;


@Repository
public interface QuestRepository extends JpaRepository<Quest, Integer> {


    List<Quest> findByCategoriaList(String categoria);



}
