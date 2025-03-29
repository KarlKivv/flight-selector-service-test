package com.cgi2025summer.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class DataStorage {
    private HashMap<String, ArrayList<Flight>> flightsByDate;
    private SimpleDateFormat format;

    public DataStorage() {
        this.flightsByDate = new HashMap<>();
        this.format = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void add(Calendar date, ArrayList<Flight> flights) {
        this.flightsByDate.put(formatDate(date), flights);
    }

    public ArrayList<Flight> getFlightsByDate(Calendar date) {
        return this.flightsByDate.get(formatDate(date));
    }

    public HashMap<String, ArrayList<Flight>> getAllFlights() {
        return this.flightsByDate;
    }

    public SimpleDateFormat getDateFormatter() {
        return this.format;
    }

    private String formatDate(Calendar date) {
        return format.format(date.getTime());
    }
}
