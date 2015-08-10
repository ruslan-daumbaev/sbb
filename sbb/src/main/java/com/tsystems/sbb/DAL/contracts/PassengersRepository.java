package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Passenger;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PassengersRepository extends CrudRepository<Passenger, Integer> {
    List<Passenger> findByFirstNameAndLastNameAndBirthDate(String firstName, String lastName, Date birthDate);
}
