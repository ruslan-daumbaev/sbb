package com.tsystems.sbb.models;

import com.tsystems.sbb.entities.Schedule;
import com.tsystems.sbb.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class ScheduleModel {
    private int id;
    private int trainId;
    private String trainTime;
    private String trainNumber;

    public ScheduleModel(){}

    public ScheduleModel(Schedule schedule){
        setTrainTimeFromDate(schedule.getTrainTime());
        setId(schedule.getId());
        if(schedule.getTrain() != null){
            setTrainNumber(schedule.getTrain().getTrainNumber());
            setTrainId(schedule.getTrain().getId());
        }
    }

    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    public void setTrainTimeFromDate(Date trainDateTime){
        trainTime = DateUtil.convertTrainTimeToString(trainDateTime);
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public static class ScheduleTrainTimeComparator implements Comparator<ScheduleModel> {

        public int compare(ScheduleModel o1, ScheduleModel o2) {
            if(o1.getTrainTime() == null || o2.getTrainTime() == null) return 0;
            return o1.getTrainTime().compareTo(o2.getTrainTime());
        }
    }
}
