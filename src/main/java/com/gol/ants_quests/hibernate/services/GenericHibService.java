package com.gol.ants_quests.hibernate.services;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gol.ants_quests.hibernate.entities.GenericEntity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class GenericHibService<E extends GenericEntity, TipoID, J extends JpaRepository<E, TipoID>> {

    private final J repository;

    public List<E> findAll() {
        log.info("findAll of " + entityType.getName());
        return repository.findAll();
    }

    public List<E> findAll(Sort sort) {
        log.info("findAll of " + entityType.getName() + ", Sorted by " + sort.toString());
        return repository.findAll(sort);
    }

    public Optional<E> findByID(TipoID tipoID) {
        log.info("findAll of " + entityType.getName() + " by ID=" + tipoID);
        return repository.findById(tipoID);
    }

    public E save(E e) {
        log.info("save of " + entityType.getName());
        return repository.save(e);
    }

    public void delete(TipoID tipoID) {
        log.info("delete of " + entityType.getName() + " by ID=" + tipoID);
        repository.deleteById(tipoID);
    }

    private final Class<E> entityType;

    @SuppressWarnings("unchecked")
    public GenericHibService(J repository) {
        this.repository = repository;
        this.entityType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

}
