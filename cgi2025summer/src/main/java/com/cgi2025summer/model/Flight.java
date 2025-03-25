package com.cgi2025summer.model;

import java.util.Random;
import java.util.UUID;

public class Flight {
    private String id;
    private String start;
    private String end;

    private long departureHour;
    private long departureMinute;

    public Flight() {
        this.id = UUID.randomUUID().toString();
        this.start = "LOC1";
        Random random = new Random();
        this.end = FlightDestinationsEnum.values()[Math
                .toIntExact(random.nextLong(FlightDestinationsEnum.values().length))]
                .name();

        this.departureHour = random.nextLong(24);
        this.departureMinute = random.nextLong(60);
    }

    public String getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
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
