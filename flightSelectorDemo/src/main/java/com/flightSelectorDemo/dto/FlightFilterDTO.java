package com.flightSelectorDemo.dto;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

import com.flightSelectorDemo.model.Flight;

public record FlightFilterDTO(
                String destinationFilter,
                @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Calendar dateTimeFilterStart,
                @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Calendar dateTimeFilterEnd,
                String dateFilter,
                ArrayList<Flight> flights) {
}
