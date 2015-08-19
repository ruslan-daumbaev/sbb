package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Schedule;
import com.tsystems.sbb.entities.Station;
import com.tsystems.sbb.entities.Train;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Date;

/**
 * Created by rdaumbae on 30.07.2015.
 */
public interface TrainsRepository extends CrudRepository<Train, Integer>, TrainsRepositoryCustom {

    @Query("select count (t) from Train t where t.trainNumber=?1")
    int getTrainsCountWithTrainNumber(String trainNumber);

    @Query("select t from Train t left join fetch t.schedules where t.id=?1")
    Train getTrainWithSchedules(int trainId);
}
