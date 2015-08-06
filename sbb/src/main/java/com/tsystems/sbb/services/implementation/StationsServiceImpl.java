package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.DAL.contracts.StationsRepository;
import com.tsystems.sbb.entities.Station;
import com.tsystems.sbb.models.StationModel;
import com.tsystems.sbb.services.contracts.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("stationsService")
public class StationsServiceImpl implements StationsService {

    private StationsRepository stationsRepository;

    public StationsRepository getStationsRepository() {
        return stationsRepository;
    }

    @Autowired
    public void setStationsRepository(StationsRepository stationsRepository) {
        this.stationsRepository = stationsRepository;
    }

    public List<StationModel> getAllStations() {
        List<Station> stations = getStationsRepository().getEntities();
        List<StationModel> stationModels = new ArrayList<StationModel>(stations.size());
        for (Station item : stations) {
            stationModels.add(new StationModel(item));
        }
        return stationModels;
    }

    @Transactional
    public void addStation(StationModel stationModel) {

        int stationId = stationModel.getId();
        String stationName = stationModel.getStationName();
        Station station;
        if(stationId == 0){
            station = new Station();
            station.setInsDate(new Date());
            station.setId(stationId);
        }
        else {
            station = getStationsRepository().getEntity(stationId);
        }
        station.setStationName(stationName);
        station.setUpdDate(new Date());
        getStationsRepository().saveEntity(station);
    }

    public StationModel getStation(int stationId) {
        Station station = getStationsRepository().getEntity(stationId);
        if(station == null){
            return new StationModel();
        }
        return new StationModel(station);
    }

}
