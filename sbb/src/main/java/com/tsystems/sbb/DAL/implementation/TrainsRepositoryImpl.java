package com.tsystems.sbb.DAL.implementation;

import com.tsystems.sbb.DAL.contracts.TrainsRepository;
import com.tsystems.sbb.entities.Schedule;
import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Date;

@Repository
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
            throw new ResourceNotFoundException();
        }
    }

    public Train getTrainWithTrips(int trainId) {
        try{
            return entityManager.createQuery("select t from Train t left join fetch t.trips where t.id=:trainId", Train.class).
                setParameter("trainId", trainId).getSingleResult();
        }
        catch (NoResultException e){
            throw new ResourceNotFoundException();
        }
    }

    public List<Schedule> getTrainsByParams(int fromStationId,
                                            int toStationId, Date fromTime, Date toTime){
        return entityManager.createQuery("select s from Schedule s " +
                "where station.id =:fromStation or station.id=:toStation " +
                "and isTrainStop = true and s.trainTime is not null " +
                "and s.trainTime >= :fromTime and s.trainTime <= :toTime " +
                        "group by s.train.id having count(s.train.id) > 1", Schedule.class)
                .setParameter("fromStation", fromStationId)
                .setParameter("toStation", toStationId).setParameter("fromTime", fromTime)
                .setParameter("toTime", toTime).getResultList();
    }
}
