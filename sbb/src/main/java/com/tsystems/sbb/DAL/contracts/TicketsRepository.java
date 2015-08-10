package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketsRepository extends CrudRepository<Ticket, Integer> {

}
