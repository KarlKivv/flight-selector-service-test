package com.flightSelectorDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightSelectorDemo.model.Flight;
import com.flightSelectorDemo.service.FlightsService;

@Controller
@RequestMapping("/flight")
public class FlightController {
    private FlightsService service;

    public FlightController(FlightsService service) {
        this.service = service;
    }

    @GetMapping
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
