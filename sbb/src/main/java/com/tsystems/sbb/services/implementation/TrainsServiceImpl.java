package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.DAL.contracts.TrainsRepository;
import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.services.contracts.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdaumbae on 30.07.2015.
 */
@Service("trainsService")
public class TrainsServiceImpl implements TrainsService {


    private TrainsRepository trainsRepository;

    public List<Train> getAllTrains() {
        return getTrainsRepository().getAllTrains();
    }

    public TrainsRepository getTrainsRepository() {
        return trainsRepository;
    }

    @Autowired
    public void setTrainsRepository(TrainsRepository trainsRepository) {
        this.trainsRepository = trainsRepository;
    }
}