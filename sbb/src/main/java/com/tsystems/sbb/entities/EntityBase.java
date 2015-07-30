package com.tsystems.sbb.entities;

import javax.persistence.*;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@MappedSuperclass
public abstract class EntityBase {
    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

}
