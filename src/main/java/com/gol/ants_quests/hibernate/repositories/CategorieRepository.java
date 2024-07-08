package com.gol.ants_quests.hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gol.ants_quests.hibernate.entities.CategoriaQuest;

@Repository
public interface CategorieRepository  extends JpaRepository<CategoriaQuest, String> {

}
