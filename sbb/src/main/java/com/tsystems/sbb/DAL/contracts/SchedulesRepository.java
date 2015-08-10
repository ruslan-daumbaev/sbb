package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Schedule;
import org.springframework.data.repository.CrudRepository;

public interface SchedulesRepository extends CrudRepository<Schedule, Integer> {
}
