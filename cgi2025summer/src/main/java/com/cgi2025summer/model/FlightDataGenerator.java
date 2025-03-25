package com.cgi2025summer.model;

import java.util.ArrayList;

public class FlightDataGenerator {
    private final ArrayList<Flight> flightsData;

    public FlightDataGenerator() {
        this.flightsData = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            flightsData.add(new Flight());
        }
    }

    public ArrayList<Flight> getFlightsData() {
        return flightsData;
    }
}
