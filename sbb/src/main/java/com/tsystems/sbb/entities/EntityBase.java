package com.tsystems.sbb.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@MappedSuperclass
public abstract class EntityBase {
    private int id;
    private Date insDate;
    private Date updDate;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "insDate")
    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    @Column(name = "updDate")
    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

}
