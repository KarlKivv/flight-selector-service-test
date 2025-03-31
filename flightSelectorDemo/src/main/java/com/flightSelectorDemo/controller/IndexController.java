package com.flightSelectorDemo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.flightSelectorDemo.model.Flight;
import com.flightSelectorDemo.service.FlightsService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @Autowired
    private FlightsService service;

    @GetMapping("/")
    public String index(Model model) {
        ArrayList<Flight> flights = service.filterFlights("", "");
        model.addAttribute("flights", flights);
        return "index";
    }

    @PostMapping("/")
    public String filterFlights(
            @RequestParam(required = false, defaultValue = "") String dateFilter,
            @RequestParam(required = false, defaultValue = "") String destinationFilter,
            Model model) {
        ArrayList<Flight> flights = service.filterFlights(dateFilter, destinationFilter);
        model.addAttribute("flights", flights);
        return "index";
    }

    @GetMapping("/flight")
    public String flightPage(@RequestParam(defaultValue = "") String id, Model model) {
        Flight flight = service.getFlightById(id);
        if (flight == null) {
            model.addAttribute("id", id);
            return "flightNotFound";
        }
        model.addAttribute("flight", flight);
        return "singleFlight";
    }
}
