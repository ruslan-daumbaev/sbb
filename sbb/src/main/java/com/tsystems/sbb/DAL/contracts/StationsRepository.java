package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Station;

import java.util.List;

public interface StationsRepository extends BaseRepository<Station> {
    Station getStationWithSchedules(int stationId);
}
