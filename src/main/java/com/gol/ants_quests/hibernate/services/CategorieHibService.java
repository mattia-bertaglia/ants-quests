package com.gol.ants_quests.hibernate.services;

import org.springframework.stereotype.Service;
import com.gol.ants_quests.hibernate.entities.CategorieQuest;
import com.gol.ants_quests.hibernate.repositories.CategorieQuestsRepository;

@Service
public class CategorieHibService extends GenericHibService<CategorieQuest, String, CategorieQuestsRepository> {

    public CategorieHibService(CategorieQuestsRepository repository) {
        super(repository);
    }

}
