package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.DAL.contracts.*;
import com.tsystems.sbb.entities.*;
import com.tsystems.sbb.exceptions.*;
import com.tsystems.sbb.models.ReportModel;
import com.tsystems.sbb.models.TicketModel;
import com.tsystems.sbb.models.TicketReportModel;
import com.tsystems.sbb.services.contracts.TicketsService;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("ticketsService")
public class TicketsServiceImpl implements TicketsService {

    @Autowired
    private TicketsRepository ticketsRepository;
    @Autowired
    private TripsRepository tripsRepository;
    @Autowired
    private SchedulesRepository schedulesRepository;
    @Autowired
    private PassengersRepository passengersRepository;

    public TicketModel getDataForTicket(int scheduleId) {
        Schedule schedule = schedulesRepository.findOne(scheduleId);
        if (schedule == null) throw new ResourceNotFoundException("Schedule", scheduleId);
        TicketModel ticketModel = new TicketModel();
        Train train = schedule.getTrain();
        ticketModel.setScheduleId(scheduleId);
        if (train != null) {
            ticketModel.setTrainNumber(train.getTrainNumber());
            ticketModel.setTrainId(train.getId());
            Calendar cal = Calendar.getInstance();
            cal.setTime(schedule.getTrainTime());
            Calendar currentTime = Calendar.getInstance();
            currentTime.setTime(new Date());

            cal.set(currentTime.get(Calendar.YEAR),
                    currentTime.get(Calendar.MONTH), currentTime.get(Calendar.DAY_OF_MONTH));

            ticketModel.setTripDate(cal.getTime());
            ticketModel.setTrainDateTime(schedule.getTrainTime());
        }
        Station station = schedule.getStation();
        if (station != null) {
            ticketModel.setStationName(station.getStationName());
        }
        return ticketModel;
    }

    @Transactional
    public void confirmTicket(TicketModel ticketModel) throws TicketException {
        Trip trip = tripsRepository.findTripByTripDateAndTrainId(ticketModel.getTripDateTime(),
                ticketModel.getTrainId());
        Schedule schedule = schedulesRepository.findOne(ticketModel.getScheduleId());
        DateTime tripDateTime = new DateTime(ticketModel.getTripDateTime());
        LocalTime trainTime = new LocalTime(schedule.getTrainTime());
        tripDateTime = tripDateTime
                .withHourOfDay(trainTime.getHourOfDay())
                .withMinuteOfHour(trainTime.getMinuteOfHour())
                .withSecondOfMinute(0);

        boolean result = Minutes.minutesBetween(new DateTime(), tripDateTime)
                .isGreaterThan(Minutes.minutes(10));
        if (!result) {
            throw new RegistrationClosedException();
        }

        if (trip == null) {
            trip = new Trip();
            trip.setInsDate(new Date());
            trip.setTripDate(ticketModel.getTripDateTime());
            trip.setTickets(new ArrayList<Ticket>());
            trip.setTrain(schedule.getTrain());
        } else {

            int ticketsCount = ticketsRepository.getTicketsCountByTripId(trip.getId());
            if (ticketsCount >= schedule.getTrain().getPlacesAmount()) {
                throw new NoFreePlacesException();
            }
            List<Ticket> tickets = ticketsRepository.findByTripIdAndPassengerFirstNameAndPassengerLastNameAndPassengerBirthDate(trip.getId(),
                    ticketModel.getFirstName(), ticketModel.getLastName(),
                    ticketModel.getBirthDate());
            if (tickets.size() > 0) {
                throw new PassenegerRegisteredException();
            }
        }
        trip.setUpdDate(new Date());
        tripsRepository.save(trip);

        Passenger passenger = new Passenger();
        passenger.setInsDate(new Date());
        passenger.setUpdDate(new Date());
        passenger.setFirstName(ticketModel.getFirstName());
        passenger.setLastName(ticketModel.getLastName());
        passenger.setBirthDate(ticketModel.getBirthDate());
        passenger.setTickets(new ArrayList<Ticket>());
        passengersRepository.save(passenger);

        Ticket ticket = new Ticket();
        ticket.setUpdDate(new Date());
        ticket.setInsDate(new Date());
        ticket.setTrip(trip);
        ticket.setPassenger(passenger);
        ticketsRepository.save(ticket);
    }

    @Override
    public ReportModel getTicketsByDate(String fromDate, String toDate) {

        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd.MM.yyyy");

        DateTime dateTimeFrom = DateTime.parse(fromDate, dtf);
        DateTime dateTimeTo = DateTime.parse(toDate, dtf);

        ReportModel reportModel = new ReportModel();
        List<Ticket> tickets = ticketsRepository.findTicketsByInsDate(dateTimeFrom.toDate(), dateTimeTo.toDate());
        for (Ticket ticket : tickets) {
            TicketReportModel ticketReportModel = new TicketReportModel();
            ticketReportModel.setTicketDate(ticket.getInsDate());
            ticketReportModel.setFirstName(ticket.getPassenger().getFirstName());
            ticketReportModel.setLastName(ticket.getPassenger().getLastName());
            DateTime birthDate = new DateTime(ticket.getPassenger().getBirthDate());
            ticketReportModel.setBirthDate(birthDate.toString(dtf));
            ticketReportModel.setTrainNumber(ticket.getTrip().getTrain().getTrainNumber());
            reportModel.addTicket(ticketReportModel);
        }
        return reportModel;
    }

}
