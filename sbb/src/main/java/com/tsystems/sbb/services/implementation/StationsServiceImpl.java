package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.DAL.contracts.StationsRepository;
import com.tsystems.sbb.entities.Schedule;
import com.tsystems.sbb.entities.Station;
import com.tsystems.sbb.models.ScheduleModel;
import com.tsystems.sbb.models.StationModel;
import com.tsystems.sbb.services.contracts.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service("stationsService")
public class StationsServiceImpl implements StationsService {
    @Autowired
    private StationsRepository stationsRepository;

    public List<StationModel> getAllStations() {
        List<Station> stations = stationsRepository.getEntities();
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
            station = stationsRepository.getEntity(stationId);
        }
        station.setStationName(stationName);
        station.setUpdDate(new Date());
        stationsRepository.saveEntity(station);
    }

    public StationModel getStation(int stationId) {
        Station station = stationsRepository.getEntity(stationId);
        if(station == null){
            return new StationModel();
        }
        return new StationModel(station);
    }

    public StationModel getStationSchedule(int stationId) {
        Station station = stationsRepository.getStationWithSchedules(stationId);
        StationModel stationModel = new StationModel(station);
        List<ScheduleModel> scheduleModels = new ArrayList<ScheduleModel>();
        if(station.getSchedules() != null){
            for (Schedule schedule: station.getSchedules()){
                if(schedule.getTrain() != null && schedule.getIsTrainStop() && schedule.getTrainTime() != null){
                    scheduleModels.add(new ScheduleModel(schedule));
                }
            }
        }
        Collections.sort(scheduleModels, new ScheduleModel.ScheduleTrainTimeComparator());
        stationModel.setSchedules(scheduleModels);
        return stationModel;
    }

}
