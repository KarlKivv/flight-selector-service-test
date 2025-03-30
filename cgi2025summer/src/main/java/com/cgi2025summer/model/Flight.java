package com.cgi2025summer.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Flight implements Comparable<Flight> {
    private String id;
    private FlightDestinationsEnum destination;
    private Calendar departureDate;

    private ArrayList<Seat> allSeats;

    public Flight() {
        Calendar departureDate = Calendar.getInstance();
        this.id = UUID.randomUUID().toString();
        Random random = new Random();
        this.destination = FlightDestinationsEnum.values()[Math
                .toIntExact(random.nextLong(FlightDestinationsEnum.values().length))];
        long departureHour = random.nextLong(24);
        long departureMinute = random.nextLong(60);

        int departureHourAsInt = Math.toIntExact(departureHour);
        int departureMinuteAsInt = Math.toIntExact(departureMinute);

        departureDate.set(Calendar.HOUR_OF_DAY, departureHourAsInt);
        departureDate.set(Calendar.MINUTE, departureMinuteAsInt);
        this.departureDate = departureDate;

        this.allSeats = new ArrayList<>();
        this.addSeats();
    }

    public String getId() {
        return this.id;
    }

    public FlightDestinationsEnum getDestination() {
        return this.destination;
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

        return this.id.compareTo(arg0.id);
    }

    private void addSeats() {
        Random random = new Random();
        for (int row = 0; row < 24; row++) {
            for (int col = 0; col < SeatColumnEnum.values().length; col++) {
                Seat seat = new Seat(row, SeatColumnEnum.values()[col]);
                if (random.nextLong(100) < 30) {
                    seat.reserveSeat();
                }
                this.allSeats.add(seat);
            }
        }
    }

    public ArrayList<Seat> getAllSeats() {
        return this.allSeats;
    }

    public String getDepartureTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(this.departureDate.getTime());
    }

    public String getDepartureDateString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(this.departureDate.getTime());
    }

    public void setDepartureDate(Calendar departureDate) {
        this.departureDate = departureDate;
    }

    public String getFlightCode() {
        return String.format("%s-%s", this.destination.getCountryISOCode(), id.split("-")[0]);
    }

    public String getAirline() {
        return String.format("Air %s", this.destination.getCountry());
    }

    public Calendar getDepartureDate() {
        return departureDate;
    }
}
