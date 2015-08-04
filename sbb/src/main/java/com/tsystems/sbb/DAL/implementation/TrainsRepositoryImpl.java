package com.tsystems.sbb.DAL.implementation;

import com.tsystems.sbb.DAL.contracts.TrainsRepository;
import com.tsystems.sbb.entities.Train;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by rdaumbae on 30.07.2015.
 */
public class TrainsRepositoryImpl implements TrainsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Train> getAllTrains() {
        return entityManager.createQuery("select t from Train t order by insDate desc", Train.class).getResultList();
    }

    public void saveTrain(Train train) {
        if(train.getId() == 0){
            entityManager.persist(train);
        }
        else{
            entityManager.merge(train);
        }
    }

    public Train getTrain(int trainId){
        return entityManager.find(Train.class, trainId);
    }
}
