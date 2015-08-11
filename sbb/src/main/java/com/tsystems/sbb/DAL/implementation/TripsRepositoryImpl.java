package com.tsystems.sbb.DAL.implementation;
import com.tsystems.sbb.DAL.contracts.TripsRepositoryCustom;
import com.tsystems.sbb.entities.Trip;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TripsRepositoryImpl implements TripsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Trip> getCurrentTrips() {
        LocalDate localDate = new LocalDate();

        return entityManager.createQuery("select t from Trip t " +
                        "join fetch t.train tr " +
                       // "join fetch tr.sc tr " +
                        "where t.tripDate >=:currentDate",
                Trip.class).setParameter("currentDate", localDate.toDate()).getResultList();
    }

    @Override
    public Trip getTripDetails(int tripId) {
        return entityManager.createQuery("select t from Trip t " +
                        //"join fetch t.schedule sc " +
                        "join fetch t.train tr " +
                        "where t.id =:id",
                Trip.class).setParameter("id", tripId).getSingleResult();
    }
}
