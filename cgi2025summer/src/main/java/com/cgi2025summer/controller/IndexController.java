package com.cgi2025summer.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgi2025summer.model.Flight;
import com.cgi2025summer.model.FlightDataGenerator;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        ArrayList<Flight> flights = new FlightDataGenerator().getFlightsData();
        flights.sort((arg0, arg1) -> arg0.getDepartureTimeString().compareTo(arg1.getDepartureTimeString()));
        model.addAttribute("flights", flights);
        return "index";
    }
}
