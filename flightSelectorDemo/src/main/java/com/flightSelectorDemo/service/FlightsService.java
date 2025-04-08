package com.flightSelectorDemo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.flightSelectorDemo.dto.FlightsDTO;
import com.flightSelectorDemo.model.DataStorage;
import com.flightSelectorDemo.model.Flight;
import com.flightSelectorDemo.model.FlightDataGenerator;

@Service
public class FlightsService {
    private DataStorage storage;
    private FlightDataGenerator generator;

    public FlightsService(DataStorage storage, FlightDataGenerator generator) {
        this.storage = storage;
        this.generator = generator;
    }

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

    // public ArrayList<Flight> getFlightsByDestination(FlightDestinationsEnum
    // destination) {
    // HashMap<String, ArrayList<Flight>> allFlights = storage.getAllFlights();
    // if (allFlights == null) {
    // return null;
    // }

    // return allFlights.values().stream()
    // .flatMap(f -> f.stream())
    // .filter(f -> f.getDestination().toString() == destination.toString())
    // .collect(Collectors.toCollection(ArrayList::new));
    // }

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

    public FlightsDTO filterFlights(FlightsDTO flightsDTO, String isDefault) {
        ArrayList<Flight> flights = new ArrayList<>();
        String destinationFilter = flightsDTO.destinationFilter();

        Calendar today = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);

        if (isDefault != null || (flightsDTO.dateTimeFilterStart() == null && flightsDTO.dateTimeFilterEnd() == null)) {
            flights = this.getFlightsByDate(today);
            flights.addAll(this.getFlightsByDate(tomorrow));
            destinationFilter = null;
        } else {
            flights = this.getFlightsWithinRange(flightsDTO.dateTimeFilterStart(), flightsDTO.dateTimeFilterEnd());
        }

        if (destinationFilter != null && !destinationFilter.isBlank()) {
            flights = this.filterByDestination(flights, flightsDTO.destinationFilter());
        }

        return new FlightsDTO(
                destinationFilter,
                flightsDTO.dateTimeFilterStart() == null ? today : flightsDTO.dateTimeFilterStart(),
                flightsDTO.dateTimeFilterEnd() == null ? tomorrow : flightsDTO.dateTimeFilterEnd(),
                flights);
    }

    public ArrayList<Flight> getFlightsWithinRange(Calendar start, Calendar end) {
        // if (end == null) {
        // end = start;
        // }
        // in case the client disables the minimum end date by editing the html
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

    // private ArrayList<Flight> getFlightsForTodayAndTomorrow() {
    // Calendar today = Calendar.getInstance();
    // Calendar tomorrow = Calendar.getInstance();
    // tomorrow.add(Calendar.DAY_OF_MONTH, 1);

    // ArrayList<Flight> flights = this.getFlightsByDate(today);
    // flights.addAll(this.getFlightsByDate(tomorrow));
    // return flights;
    // }

    private ArrayList<Flight> filterByDestination(ArrayList<Flight> flights, String destinationFilter) {
        return flights.stream()
                .filter(f -> f.getDestination()
                        .getDestinationString()
                        .toLowerCase()
                        .contains(destinationFilter))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
