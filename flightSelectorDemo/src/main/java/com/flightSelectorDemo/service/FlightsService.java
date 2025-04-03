package com.flightSelectorDemo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightSelectorDemo.dto.FlightFilterDTO;
import com.flightSelectorDemo.model.DataStorage;
import com.flightSelectorDemo.model.Flight;
import com.flightSelectorDemo.model.FlightDataGenerator;
import com.flightSelectorDemo.model.FlightDestinationsEnum;

@Service
public class FlightsService {
    @Autowired
    private DataStorage storage;

    @Autowired
    private FlightDataGenerator generator;

    public ArrayList<Flight> getFlightsByDate(Calendar date) {
        ArrayList<Flight> flights = this.storage.getFlightsByDate(date);
        if (flights == null) {
            flights = generator.generate(date);
            storage.add(date, flights);
        }
        return flights.stream()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Flight> getFlightsByDestination(FlightDestinationsEnum destination) {
        HashMap<String, ArrayList<Flight>> allFlights = storage.getAllFlights();
        if (allFlights == null) {
            return null;
        }

        return allFlights.values().stream()
                .flatMap(f -> f.stream())
                .filter(f -> f.getDestination().toString() == destination.toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Flight> getAllFlights() {
        HashMap<String, ArrayList<Flight>> allFlights = storage.getAllFlights();
        if (allFlights == null) {
            return null;
        }

        return allFlights.values().stream()
                .flatMap(f -> f.stream())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Flight getFlightById(String id) {
        for (Flight f : this.getAllFlights()) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    public ArrayList<Flight> filterFlights(FlightFilterDTO flightFilterDTO) {
        if (flightFilterDTO.destinationFilter() != null &&
                !flightFilterDTO.destinationFilter().isBlank()) {
            return this.filterByDestination(flightFilterDTO.destinationFilter());
        }
        if (flightFilterDTO.dateTimeFilterStart() == null && flightFilterDTO.dateTimeFilterEnd() == null) {
            return this.getFlightsForTodayAndTomorrow();
        }
        return this.getFlightsWithinRange(flightFilterDTO.dateTimeFilterStart(), flightFilterDTO.dateTimeFilterEnd());
    }

    public ArrayList<Flight> getFlightsWithinRange(Calendar start, Calendar end) {
        // in case someone disables the minimum date by editing the html
        if (end.before(start)) {
            throw new IllegalArgumentException("start time is before start time");
        }

        ArrayList<Flight> flights = new ArrayList<>();

        while (start.before(end)) {
            flights.addAll(getFlightsByDate(start));
            start.add(Calendar.DAY_OF_MONTH, 1);
        }

        // while loop is off by one
        flights.addAll(getFlightsByDate(end));
        return flights;
    }

    private ArrayList<Flight> getFlightsForTodayAndTomorrow() {
        Calendar today = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);

        ArrayList<Flight> flights = this.getFlightsByDate(today);
        flights.addAll(this.getFlightsByDate(tomorrow));
        return flights;
    }

    private ArrayList<Flight> filterByDestination(String destinationFilter) {
        // this value is temporary, should use getFlightsWithinRange
        ArrayList<Flight> flights = this.getFlightsForTodayAndTomorrow();
        return flights.stream()
                .filter(f -> f.getDestination()
                        .getDestinationString()
                        .toLowerCase()
                        .contains(destinationFilter))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
