package com.cgi2025summer.model;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class FlightDataGenerator {
    public ArrayList<Flight> generate(Calendar date) {
        ArrayList<Flight> flightsData = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            flightsData.add(new Flight(date));
        }
        return flightsData;
    }
}
