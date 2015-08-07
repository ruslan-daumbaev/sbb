package com.tsystems.sbb.DAL.implementation;

import com.tsystems.sbb.DAL.contracts.StationsRepository;
import com.tsystems.sbb.entities.Station;
import com.tsystems.sbb.entities.Train;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
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

    public Station getStationWithSchedules(int stationId) {
        try{
            return entityManager.createQuery("select t from Station t " +
                    "left join fetch t.schedules s " +
                    "left join fetch s.train where t.id=:stationId", Station.class).
                    setParameter("stationId", stationId).getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }
}
