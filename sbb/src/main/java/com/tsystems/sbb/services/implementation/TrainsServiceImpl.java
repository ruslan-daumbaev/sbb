package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.DAL.contracts.StationsRepository;
import com.tsystems.sbb.DAL.contracts.TrainsRepository;
import com.tsystems.sbb.DAL.contracts.TripsRepository;
import com.tsystems.sbb.entities.Schedule;
import com.tsystems.sbb.entities.Station;
import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.models.ScheduleModel;
import com.tsystems.sbb.models.StationModel;
import com.tsystems.sbb.models.TrainModel;
import com.tsystems.sbb.services.contracts.TrainsService;
import org.joda.time.LocalTime;
import org.joda.time.ReadableInstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("trainsService")
public class TrainsServiceImpl implements TrainsService {
    @Autowired
    private TrainsRepository trainsRepository;

    @Autowired
    private StationsRepository stationsRepository;


    public List<TrainModel> getAllTrains() {
        List<Train> trains = trainsRepository.getEntities();
        List<TrainModel> trainModels = new ArrayList<TrainModel>(trains.size());
        for (Train item : trains) {
            trainModels.add(new TrainModel(item));
        }
        return trainModels;
    }

    @Transactional
    public void addTrain(TrainModel trainModel) {

        Train train;
        int trainId = trainModel.getId();
        String trainNumber = trainModel.getTrainNumber();
        int placesAmount = trainModel.getPlacesAmount();

        if(trainId == 0){
            train = new Train();
            train.setSchedules(new ArrayList<Schedule>());
            train.setInsDate(new Date());
            train.setId(trainId);
        }
        else {
            train = trainsRepository.getTrainWithSchedules(trainId);
        }

        train.setTrainNumber(trainNumber);
        train.setPlacesAmount(placesAmount);
        train.setUpdDate(new Date());
        if(trainModel.getStations() != null){
            for (StationModel stationModel : trainModel.getStations()) {
                Station station = stationsRepository.getEntity(stationModel.getId());
                Schedule schedule = findSchedule(train, stationModel);

                schedule.setUpdDate(new Date());
                schedule.setIsTrainStop(stationModel.getIsSelected());
                schedule.setTrain(train);
                schedule.setStation(station);
                schedule.setTrainTime(stationModel.getTrainDateTime());
                train.getSchedules().add(schedule);
            }
        }
        trainsRepository.saveEntity(train);
    }

    public TrainModel getTrain(int trainId){
        Train train = trainsRepository.getTrainWithSchedules(trainId);
        List<Station> stations = stationsRepository.getEntities();
        TrainModel trainModel = train == null ? new TrainModel() : new TrainModel(train);
        trainModel.setStations(new ArrayList<StationModel>(stations.size()));
        for (Station item : stations) {
            StationModel stationModel = new StationModel(item);
            trainModel.getStations().add(stationModel);
            if(train != null && train.getSchedules() != null){
                for (Schedule schedule: train.getSchedules()){
                    if(schedule.getStation().getId() == item.getId()){
                        stationModel.setIsSelected(schedule.getIsTrainStop());
                        stationModel.setTrainTimeFromDate(schedule.getTrainTime());
                    }
                }
            }
        }
        return trainModel;
    }

    public List<ScheduleModel> findTrainsByParams(int fromStationId,
                                                  int toStationId, String fromTime, String toTime) {
        if(fromStationId == 0 || toStationId == 0){
            return new ArrayList<ScheduleModel>();
        }
        LocalTime fromDateTime = (fromTime != null && !fromTime.isEmpty())
                ? new LocalTime(fromTime) : new LocalTime("00:00");
        LocalTime toDateTime = (toTime != null  && !toTime.isEmpty())
                ? new LocalTime(toTime) : new LocalTime("23:59");
        List<Schedule> schedules = trainsRepository.getTrainsByParams(fromStationId, toStationId, fromDateTime.toDateTimeToday().toDate(),
                toDateTime.toDateTimeToday().toDate());
        List<ScheduleModel> scheduleModels = new ArrayList<ScheduleModel>(schedules.size());

        for(Schedule schedule: schedules){
            if(schedule.getIsTrainStop() == false || schedule.getTrainTime() == null)
            {
                continue;
            }
            LocalTime fromEnt = new LocalTime(schedule.getTrainTime());
            if(fromEnt.isBefore(fromDateTime) || fromEnt.isAfter(toDateTime))
            {
                continue;
            }
            ScheduleModel scheduleModel = new ScheduleModel(schedule);
            scheduleModels.add(scheduleModel);
        }
        return scheduleModels;
    }

    private Schedule findSchedule(Train train, StationModel stationModel){
        for (Schedule existSchedule: train.getSchedules()){
            if(existSchedule.getStation().getId() == stationModel.getId()){
                return existSchedule;
            }
        }
        Schedule schedule = new Schedule();
        schedule.setInsDate(new Date());
        return schedule;
    }

}
