package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.DAL.contracts.TrainsRepository;
import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.services.contracts.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Train> getAllTrains() {
        return getTrainsRepository().getAllTrains();
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

    public Train getTrain(int trainId){
        return trainsRepository.getTrain(trainId);
    }
}
