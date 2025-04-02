package com.flightSelectorDemo.dto;

public record FlightFilterDTO(
        String destinationFilter,
        String dateTimeFilterStart,
        String dateTimeFilterEnd,
        String dateFilter) {
}
