package com.flightSelectorDemo.service;

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
        return this.filterByDate(flightFilterDTO.dateFilter());
    }

    // public ArrayList<Flight> filterFlights(String dateTimeFilterStart, String
    // dateTimeFilterEnd,
    // String destinationFilter) {
    // if (!destinationFilter.isBlank()) {
    // return this.filterByDestination(destinationFilter);
    // }
    // return this.filterByDateInRange(dateTimeFilterStart, dateTimeFilterEnd);
    // }

    // public ArrayList<Flight> filterByDateInRange(String dateTimeFilterStart,
    // String dateTimeFilterEnd) {
    // Calendar start = Calendar.getInstance();
    // Calendar tomorrow = Calendar.getInstance();
    // tomorrow.add(Calendar.DAY_OF_MONTH, 1);

    // switch (dateFilter) {
    // case "today":
    // return this.getFlightsByDate(today);
    // case "tomorrow":
    // return this.getFlightsByDate(tomorrow);
    // default:
    // return getFlightsForTodayAndTomorrow();
    // }
    // }

    private ArrayList<Flight> getFlightsForTodayAndTomorrow() {
        Calendar today = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);

        ArrayList<Flight> flights = this.getFlightsByDate(today);
        flights.addAll(this.getFlightsByDate(tomorrow));
        return flights;
    }

    private ArrayList<Flight> filterByDate(String dateFilter) {
        Calendar today = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);

        if (dateFilter == null || dateFilter.isBlank()) {
            return getFlightsForTodayAndTomorrow();
        }

        switch (dateFilter) {
            case "today":
                return this.getFlightsByDate(today);
            case "tomorrow":
                return this.getFlightsByDate(tomorrow);
            default:
                throw new IllegalArgumentException(String.format("unknown dateFilter value: %s", dateFilter));
        }
    }

    private ArrayList<Flight> filterByDestination(String destinationFilter) {
        ArrayList<Flight> flights = this.filterByDate("");
        return flights.stream()
                .filter(f -> f.getDestination()
                        .getDestinationString()
                        .toLowerCase()
                        .contains(destinationFilter))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
