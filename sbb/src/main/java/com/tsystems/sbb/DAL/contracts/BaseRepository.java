package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.EntityBase;

import java.util.List;

public interface BaseRepository<T>  {

    List<T> getEntities();

    T getEntity(int entityId);

    void saveEntity(T entity);
}
