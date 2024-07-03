
package com.gol.ants_quests.hibernate.services;

import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieHibService extends GenericHibService<CategoriaQuest, String, CategorieRepository>{


    public CategorieHibService(CategorieRepository repository) {
        super(repository);
    }


    @Autowired
    private CategorieRepository categoriaRepository;

    public List<CategoriaQuest> getAllCategories() {
        return categoriaRepository.findAll();
    }

    public Optional<CategoriaQuest> getCategoryById(String id) {
        return categoriaRepository.findById(id);
    }

    public CategoriaQuest createCategory(CategoriaQuest categoriaQuest) {
        return categoriaRepository.save(categoriaQuest);
    }

    public CategoriaQuest updateCategory(CategoriaQuest categoriaQuest) {
        return categoriaRepository.save(categoriaQuest);
    }

    public void deleteCategory(String id) {
        categoriaRepository.deleteById(id);
    }
}
