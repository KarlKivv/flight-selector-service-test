package com.cgi2025summer.model;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Flight {
    private String id;
    private String destination;

    private long departureHour;
    private long departureMinute;

    public Flight() {
        this.id = UUID.randomUUID().toString();
        Random random = new Random();
        this.destination = FlightDestinationsEnum.values()[Math
                .toIntExact(random.nextLong(FlightDestinationsEnum.values().length))]
                .toString();

        this.departureHour = random.nextLong(24);
        this.departureMinute = random.nextLong(60);
    }

    public String getId() {
        return this.id;
    }

    public String getDestination() {
        return this.destination;
    }

    // tried to use Date and Calendar types, but they seem to be depracated since
    // Java 8+ and I got tired of messing with Temporal-s
    public String getDepartureTimeString() {
        StringBuilder builder = new StringBuilder();
        if (this.departureHour < 10) {
            builder.append(0);
        }
        builder.append(this.departureHour);
        builder.append(":");
        if (this.departureMinute < 10) {
            builder.append(0);
        }
        builder.append(this.departureMinute);
        return builder.toString();
    }
}
