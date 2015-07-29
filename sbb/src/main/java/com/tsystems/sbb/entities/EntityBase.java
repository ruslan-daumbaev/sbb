package com.tsystems.sbb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@Entity
public abstract class EntityBase {
    @Id
    private int id;
}
