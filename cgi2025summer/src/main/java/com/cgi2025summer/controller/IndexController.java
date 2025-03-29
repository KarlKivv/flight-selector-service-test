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

    @GetMapping("/")
    public String index(Model model) {
        ArrayList<Flight> flights = service.filterFlights("");
        model.addAttribute("flights", flights);
        return "index";
    }

    @PostMapping("/")
    public String filter(@RequestParam String filter, Model model) {
        ArrayList<Flight> flights = service.filterFlights(filter);
        model.addAttribute("flights", flights);
        return "index";
    }

    @GetMapping("/flight")
    public String getMethodName(@RequestParam String id, Model model) {
        Flight flight = service.getFlightById(id);
        if (flight == null) {
            model.addAttribute("id", id);
            return "flightNotFound";
        }
        model.addAttribute("flight", flight);
        return "singleFlight";
    }
}
