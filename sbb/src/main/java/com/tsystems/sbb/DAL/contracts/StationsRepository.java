package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Station;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StationsRepository extends CrudRepository<Station, Integer> {

    @Query("select t from Station t left join fetch t.schedules s " +
            "left join fetch s.train where t.id=?1")
    Station getStationWithSchedules(int stationId);
}
