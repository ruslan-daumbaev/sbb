package com.tsystems.sbb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@Entity
public class Passenger extends EntityBase {
    private String firstName;
    private String lastName;
    private Date birthDate;
}
