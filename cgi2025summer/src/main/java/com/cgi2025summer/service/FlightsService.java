package com.cgi2025summer.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi2025summer.model.DataStorage;
import com.cgi2025summer.model.Flight;
import com.cgi2025summer.model.FlightDataGenerator;
import com.cgi2025summer.model.FlightDestinationsEnum;

@Service
public class FlightsService {
    @Autowired
    private DataStorage storage;

    @Autowired
    private FlightDataGenerator generator;

    public ArrayList<Flight> getFlightsByDate(Calendar date) {
        ArrayList<Flight> flights = this.storage.getFlightsByDate(date);
        if (flights == null) {
            flights = generator.generate();
            storage.add(date, flights);
        }
        return flights;
    }

    public ArrayList<Flight> getFlightsByDestination(FlightDestinationsEnum destination) {
        HashMap<Calendar, ArrayList<Flight>> allFlights = this.storage.getAllFlights();
        if (allFlights == null) {
            return null;
        }
        return allFlights.values().stream()
                .flatMap(f -> f.stream())
                .filter(f -> f.getDestination() == destination.toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
