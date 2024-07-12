package com.gol.ants_quests.hibernate.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gol.ants_quests.hibernate.entities.GenericEntity;

import lombok.Data;

@Data
public class GenericHibService<E extends GenericEntity, TipoID, J extends JpaRepository<E, TipoID>> {

    private final J repository;

    public List<E> findAll() {
        return repository.findAll();
    }

    public List<E> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public Optional<E> findByID(TipoID tipoID) {
        return repository.findById(tipoID);
    }

    public E save(E e) {
        return repository.save(e);
    }

    public void delete(TipoID stud) {
        repository.deleteById(stud);
    }

}
