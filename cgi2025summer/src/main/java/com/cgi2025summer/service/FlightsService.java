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

    // private Calendar today;
    // private Calendar tomorrow;

    // getFlightsByDate(Calendar date)
    // show all flights for date
    // if no data for date, generate and add to storage
    // for index

    // getFlightsByDestination(DestinationEnum destination)
    // show all flights going to destination
    // if no data return null
    // for search

    // public FlightsService(DataStorage storage, FlightDataGenerator generator) {
    // this.storage = storage;
    // this.generator = generator;

    // this.today = Calendar.getInstance();
    // this.tomorrow = Calendar.getInstance();
    // this.tomorrow.add(Calendar.DAY_OF_MONTH, 1);

    // initData();
    // }

    // private void initData() {
    // ArrayList<Flight> flightsToday = generator.generate();
    // ArrayList<Flight> flightsTomorrow = generator.generate();

    // storage.add(this.today, flightsToday);
    // storage.add(this.tomorrow, flightsTomorrow);
    // }

    // public ArrayList<Flight> getAllFlights(Calendar date) {
    // return this.storage.getAllFlights().values().stream()
    // .flatMap(f -> f.stream())
    // .collect(Collectors.toCollection(ArrayList::new));
    // }

    public ArrayList<Flight> getFlightsByDate(Calendar date) {
        ArrayList<Flight> flights = this.storage.getFlightsByDate(date);
        if (flights == null) {
            flights = generator.generate();
            storage.add(date, flights);
        }
        return flights.stream()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Flight> getFlightsByDestination(FlightDestinationsEnum destination) {
        HashMap<Calendar, ArrayList<Flight>> allFlights = storage.getAllFlights();
        if (allFlights == null) {
            return null;
        }

        return allFlights.values().stream()
                .flatMap(f -> f.stream())
                .filter(f -> f.getDestination() == destination.toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // public ArrayList<Flight> filterFlightsByDestination(HashMap<Calendar,
    // ArrayList<Flight>> allFlights,
    // FlightDestinationsEnum destination) {

    // // if (allFlights == null) {
    // // return null;
    // // }

    // return allFlights.values().stream()
    // .flatMap(f -> f.stream())
    // .filter(f -> f.getDestination() == destination.toString())
    // .collect(Collectors.toCollection(ArrayList::new));
    // }

    // public ArrayList<Flight> getFilteredFlights(Calendar date,
    // FlightDestinationsEnum destination) {
    // if (date == null) {
    // date = Calendar.getInstance();
    // }

    // if (destination == null) {
    // return this.getFlightsByDate(date);
    // } else {
    // HashMap<Calendar, ArrayList<Flight>> allFlights =
    // this.storage.getAllFlights();
    // return filterFlightsByDestination(allFlights, destination);
    // }
    // }

    // public ArrayList<Flight> getFilteredAndSortedByDateFlights(Calendar date,
    // FlightDestinationsEnum destination) {
    // ArrayList<Flight> flights = getFilteredFlights(date, destination);
    // if (flights == null) {
    // return null;
    // }
    // flights.stream().sorted();
    // return flights;
    // }
}
