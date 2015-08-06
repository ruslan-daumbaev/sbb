package com.tsystems.sbb.DAL.implementation;

import com.tsystems.sbb.DAL.contracts.TrainsRepository;
import com.tsystems.sbb.entities.Train;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by rdaumbae on 30.07.2015.
 */
public class TrainsRepositoryImpl extends BaseRepositoryImpl implements TrainsRepository {
    public List<Train> getEntities() {
        return entityManager.createQuery("select t from Train t order by insDate desc", Train.class).getResultList();
    }

    public void saveEntity(Train entity) {
        saveEntityBase(entity);
    }

    public Train getEntity(int entityId){

        return entityManager.find(Train.class, entityId);
    }

    public Train getTrainWithSchedules(int trainId) {
        try{
            return entityManager.createQuery("select t from Train t left join fetch t.schedules where t.id=:trainId", Train.class).
                setParameter("trainId", trainId).getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }
}
