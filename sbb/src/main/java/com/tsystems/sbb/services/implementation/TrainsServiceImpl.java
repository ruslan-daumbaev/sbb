package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.services.contracts.TrainsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdaumbae on 30.07.2015.
 */
@Service("trainsService")
public class TrainsServiceImpl implements TrainsService {
    public List<Train> getAllTrains() {
        List<Train> trains = new ArrayList<Train>();
        for(int i = 0; i < 10;i++){
            Train t = new Train();
            t.setTrainNumber("T" + i);
            t.setPlacesAmount(i);
            trains.add(t);
        }
        return trains;
    }
}
