package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.DAL.contracts.StationsRepository;
import com.tsystems.sbb.DAL.contracts.TrainsRepository;
import com.tsystems.sbb.entities.Schedule;
import com.tsystems.sbb.entities.Station;
import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.models.ScheduleModel;
import com.tsystems.sbb.models.StationModel;
import com.tsystems.sbb.models.TrainModel;
import com.tsystems.sbb.services.contracts.TrainsService;
import org.joda.time.LocalTime;
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
        Iterable<Train> trains = trainsRepository.findAll();
        List<TrainModel> trainModels = new ArrayList<>();
        for (Train item : trains) {
            trainModels.add(new TrainModel(item));
        }
        return trainModels;
    }

    @Transactional
    public void saveTrain(TrainModel trainModel) {

        Train train;
        int trainId = trainModel.getId();
        String trainNumber = trainModel.getTrainNumber();
        int placesAmount = trainModel.getPlacesAmount();

        if (trainId == 0) {
            train = new Train();
            train.setSchedules(new ArrayList<Schedule>());
            train.setInsDate(new Date());
            train.setId(trainId);
        } else {
            train = trainsRepository.getTrainWithSchedules(trainId);
        }

        train.setTrainNumber(trainNumber);
        train.setPlacesAmount(placesAmount);
        train.setUpdDate(new Date());
        if (trainModel.getStations() != null) {
            for (StationModel stationModel : trainModel.getStations()) {
                Station station = stationsRepository.findOne(stationModel.getId());
                Schedule schedule = findSchedule(train, stationModel);

                schedule.setUpdDate(new Date());
                schedule.setIsTrainStop(stationModel.getIsSelected());
                schedule.setTrain(train);
                schedule.setStation(station);
                schedule.setTrainTime(stationModel.getTrainDateTime());
                train.getSchedules().add(schedule);
            }
        }
        trainsRepository.save(train);
    }

    public TrainModel getTrain(int trainId) {
        Train train = null;
        if (trainId != 0) {
            train = trainsRepository.getTrainWithSchedules(trainId);
        }
        Iterable<Station> stations = stationsRepository.findAll();
        TrainModel trainModel = train == null ? new TrainModel() : new TrainModel(train);
        trainModel.setStations(new ArrayList<StationModel>());
        for (Station item : stations) {
            StationModel stationModel = new StationModel(item);
            trainModel.getStations().add(stationModel);
            if (train == null || train.getSchedules() == null) {
                continue;
            }
            for (Schedule schedule : train.getSchedules()) {
                if (schedule.getStation().getId() == item.getId()) {
                    stationModel.setIsSelected(schedule.getIsTrainStop());
                    stationModel.setTrainTimeFromDate(schedule.getTrainTime());
                }
            }
        }
        return trainModel;
    }

    public List<ScheduleModel> findTrainsByParams(int fromStationId,
                                                  int toStationId, String fromTime, String toTime) {
        if (fromStationId == 0 || toStationId == 0) {
            return new ArrayList<>();
        }
        LocalTime fromDateTime = (fromTime != null && !fromTime.isEmpty())
                ? new LocalTime(fromTime) : new LocalTime("00:00");
        LocalTime toDateTime = (toTime != null && !toTime.isEmpty())
                ? new LocalTime(toTime) : new LocalTime("23:59");
        List<Schedule> schedules = trainsRepository.getTrainsByParams(fromStationId, toStationId);
        List<ScheduleModel> scheduleModels = new ArrayList<>(schedules.size());

        for (Schedule schedule : schedules) {
            if (!schedule.getIsTrainStop() || schedule.getTrainTime() == null) {
                continue;
            }
            LocalTime fromEnt = new LocalTime(schedule.getTrainTime());
            if (fromEnt.isBefore(fromDateTime) || fromEnt.isAfter(toDateTime)) {
                continue;
            }
            ScheduleModel scheduleModel = new ScheduleModel(schedule);
            scheduleModels.add(scheduleModel);
        }
        return scheduleModels;
    }

    @Override
    public boolean checkTrainNumber(String trainNumber) {
        return trainsRepository.getTrainsCountWithTrainNumber(trainNumber) == 0;
    }

    private Schedule findSchedule(Train train, StationModel stationModel) {
        for (Schedule existSchedule : train.getSchedules()) {
            if (existSchedule.getStation().getId() == stationModel.getId()) {
                return existSchedule;
            }
        }
        Schedule schedule = new Schedule();
        schedule.setInsDate(new Date());
        return schedule;
    }

}
