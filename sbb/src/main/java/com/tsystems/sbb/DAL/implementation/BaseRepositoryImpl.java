package com.tsystems.sbb.DAL.implementation;

import com.tsystems.sbb.DAL.contracts.BaseRepository;
import com.tsystems.sbb.entities.EntityBase;
import com.tsystems.sbb.entities.Train;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class BaseRepositoryImpl {

    @PersistenceContext
    protected EntityManager entityManager;

    public void saveEntityBase(EntityBase entity) {
        if(entity.getId() == 0){
            entityManager.persist(entity);
        }
        else{
            entityManager.merge(entity);
        }
    }
}
