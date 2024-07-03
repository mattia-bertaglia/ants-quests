package com.gol.ants_quests.services;

import org.springframework.stereotype.Service;
import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.repositories.CategorieRepository;

@Service
public class CategorieHibService extends GenericHibService<CategoriaQuest, String, CategorieRepository>{

    public CategorieHibService(CategorieRepository repository) {
        super(repository);
    }



}
