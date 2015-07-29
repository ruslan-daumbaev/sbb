package com.tsystems.sbb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@Entity
public class Train  extends EntityBase  {
    private int trainNumber;
    private int placesAmount;
}
