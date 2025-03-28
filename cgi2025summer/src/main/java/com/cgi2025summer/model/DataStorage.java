package com.cgi2025summer.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class DataStorage {
    private HashMap<Calendar, ArrayList<Flight>> flightsByDate;

    public DataStorage() {
        this.flightsByDate = new HashMap<>();
    }

    public void add(Calendar date, ArrayList<Flight> flights) {
        this.flightsByDate.put(date, flights);
    }

    public ArrayList<Flight> getFlightsByDate(Calendar date) {
        return this.flightsByDate.get(date);
    }

    public HashMap<Calendar, ArrayList<Flight>> getAllFlights() {
        return this.flightsByDate;
    }
}
