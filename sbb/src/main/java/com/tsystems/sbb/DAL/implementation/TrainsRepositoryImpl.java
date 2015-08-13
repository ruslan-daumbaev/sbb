package com.tsystems.sbb.DAL.implementation;
import com.tsystems.sbb.DAL.contracts.TrainsRepositoryCustom;
import com.tsystems.sbb.entities.Schedule;
import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TrainsRepositoryImpl implements TrainsRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

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
                                            int toStationId){
        return entityManager.createQuery("select s from Schedule s " +
                "where station.id =:fromStation or station.id=:toStation " +
                "and isTrainStop = true and s.trainTime is not null " +
                        "group by s.train.id having count(s.train.id) > 1 order by s.trainTime", Schedule.class)
                .setParameter("fromStation", fromStationId)
                .setParameter("toStation", toStationId)
                .getResultList();
    }
}
