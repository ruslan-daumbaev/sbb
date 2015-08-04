package com.tsystems.sbb.DAL.implementation;

import com.tsystems.sbb.DAL.contracts.StationsRepository;
import com.tsystems.sbb.entities.Station;
import com.tsystems.sbb.entities.Train;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class StationsRepositoryImpl implements StationsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Station> getStations() {
        return entityManager.createQuery("select t from Station t order by insDate desc", Station.class).getResultList();
    }

    public Station getStation(int stationId) {
        return entityManager.find(Station.class, stationId);
    }

    public void saveStation(Station station) {
        if(station.getId() == 0){
            entityManager.persist(station);
        }
        else{
            entityManager.merge(station);
        }
    }
}
