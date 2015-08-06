package com.tsystems.sbb.DAL.implementation;

import com.tsystems.sbb.DAL.contracts.StationsRepository;
import com.tsystems.sbb.entities.Station;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class StationsRepositoryImpl implements StationsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Station> getEntities() {
        return entityManager.createQuery("select t from Station t order by insDate desc", Station.class).getResultList();
    }

    public Station getEntity(int entityId) {
        return entityManager.find(Station.class, entityId);
    }

    public void saveEntity(Station entity) {
        if(entity.getId() == 0){
            entityManager.persist(entity);
        }
        else{
            entityManager.merge(entity);
        }
    }
}
