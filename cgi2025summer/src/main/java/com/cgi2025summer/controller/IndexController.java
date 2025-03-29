package com.cgi2025summer.controller;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgi2025summer.model.Flight;
import com.cgi2025summer.model.FlightDestinationsEnum;
import com.cgi2025summer.service.FlightsService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @Autowired
    private FlightsService service;

    @RequestMapping("/")
    public String index(
            Model model,
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "MM/dd/yyyy") Calendar date,
            @RequestParam(name = "destination", required = false, defaultValue = "") FlightDestinationsEnum destination) {

        ArrayList<Flight> flights = this.service.getFlightsByDate(Calendar.getInstance());
        model.addAttribute("flights", flights);
        return "index";
    }

    @GetMapping("/flights/{flightId}")
    public String getMethodName(@RequestParam String flightId) {
        return new String();
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model) {
        ArrayList<Flight> flights;
        Calendar today = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);

        switch (filter) {
            case "today":
                flights = service.getFlightsByDate(today);
                break;
            case "tomorrow":
                flights = service.getFlightsByDate(tomorrow);
                break;
            default:
                flights = service.getFlightsByDate(today);
                flights.addAll(service.getFlightsByDate(tomorrow));
                break;
        }
        model.addAttribute("flights", flights);
        return "index";
    }
}
