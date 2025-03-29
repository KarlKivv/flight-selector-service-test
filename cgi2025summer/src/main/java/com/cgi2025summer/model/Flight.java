package com.cgi2025summer.model;

import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Flight implements Comparable<Flight> {
    private String id;
    private String destination;

    private Calendar departureDate;

    // private long departureHour;
    // private long departureMinute;

    public Flight() {
        this.id = UUID.randomUUID().toString();
        Random random = new Random();
        this.destination = FlightDestinationsEnum.values()[Math
                .toIntExact(random.nextLong(FlightDestinationsEnum.values().length))].toString();
        long departureHour = random.nextLong(24);
        long departureMinute = random.nextLong(60);

        // SimpleDateFormat formatter = new SimpleDateFormat("dd MMM YYYY HH:mm",
        // Locale.ENGLISH);
        int departureHourAsInt = Math.toIntExact(departureHour);
        int departureMinuteAsInt = Math.toIntExact(departureMinute);

        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR_OF_DAY, departureHourAsInt);
        instance.set(Calendar.MINUTE, departureMinuteAsInt);
        this.departureDate = instance;
    }

    public String getId() {
        return this.id;
    }

    public String getDestination() {
        return this.destination;
    }

    // tried to use Date and Calendar types, but they seem to be depracated since
    // Java 8+ and I got tired of messing with Temporal-s
    // public String getDepartureTimeString() {
    // StringBuilder builder = new StringBuilder();
    // if (this.departureHour < 10) {
    // builder.append(0);
    // }
    // builder.append(this.departureHour);
    // builder.append(":");
    // if (this.departureMinute < 10) {
    // builder.append(0);
    // }
    // builder.append(this.departureMinute);
    // return builder.toString();
    // }

    public String getDepartureTimeString() {
        return String.format("%1$tH:%1$tM", this.departureDate);
    }

    @Override
    public int compareTo(Flight arg0) {
        if (this.id.equals(arg0.id)) {
            return 0;
        }

        int calendarComp = this.departureDate.compareTo(arg0.departureDate);
        if (calendarComp != 0) {
            if (calendarComp > 0) {
                return 1;
            } else {
                return -1;
            }
        }

        int destComp = this.destination.compareTo(arg0.destination);
        if (destComp != 0) {
            return destComp;
        }

        // long hourComp = this.departureHour - arg0.departureHour;
        // if (hourComp != 0) {
        // if (hourComp > 0) {
        // return 1;
        // } else {
        // return -1;
        // }
        // }
        // long minuteComp = this.departureMinute - arg0.departureMinute;
        // if (minuteComp != 0) {
        // if (minuteComp > 0) {
        // return 1;
        // } else {
        // return -1;
        // }
        // }
        return this.id.compareTo(arg0.id);
    }

}
