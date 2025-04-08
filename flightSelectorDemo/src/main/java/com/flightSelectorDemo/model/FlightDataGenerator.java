package com.flightSelectorDemo.model;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class FlightDataGenerator {
    public ArrayList<Flight> generate(Calendar date) {
        ArrayList<Flight> flightsData = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            flightsData.add(new Flight());
        }

        flightsData.stream().forEach(f -> {
            f.getDepartureDate().set(Calendar.YEAR, date.get(Calendar.YEAR));
            f.getDepartureDate().set(Calendar.MONTH, date.get(Calendar.MONTH));
            f.getDepartureDate().set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
        });

        return flightsData;
    }
}
