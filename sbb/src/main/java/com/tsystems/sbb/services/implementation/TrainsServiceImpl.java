package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.DAL.contracts.TrainsRepository;
import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.models.TrainModel;
import com.tsystems.sbb.services.contracts.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("trainsService")
public class TrainsServiceImpl implements TrainsService {

    private TrainsRepository trainsRepository;


    public TrainsRepository getTrainsRepository() {
        return trainsRepository;
    }

    @Autowired
    public void setTrainsRepository(TrainsRepository trainsRepository) {
        this.trainsRepository = trainsRepository;
    }

    public List<TrainModel> getAllTrains() {
        List<Train> trains = getTrainsRepository().getAllTrains();
        List<TrainModel> trainModels = new ArrayList<TrainModel>(trains.size());
        for (Train item : trains) {
            trainModels.add(new TrainModel(item));
        }
        return trainModels;
    }

    @Transactional
    public void addTrain(int trainId, String trainNumber, int placesAmount) {
        Train train;
        if(trainId == 0){
            train = new Train();
            train.setInsDate(new Date());
            train.setId(trainId);
        }
        else {
            train = trainsRepository.getTrain(trainId);
        }
        train.setTrainNumber(trainNumber);
        train.setPlacesAmount(placesAmount);
        train.setUpdDate(new Date());
        trainsRepository.saveTrain(train);
    }

    public TrainModel getTrain(int trainId){
        Train train = trainsRepository.getTrain(trainId);
        return new TrainModel(train);
    }
}
