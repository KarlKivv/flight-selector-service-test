package com.flightSelectorDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.flightSelectorDemo.dto.FlightsDTO;
import com.flightSelectorDemo.service.FlightsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    private FlightsService service;

    public IndexController(FlightsService service) {
        this.service = service;
    }

    @GetMapping
    public String index(@ModelAttribute FlightsDTO fromClient, Model model) {
        FlightsDTO toClient = service.filterFlights(fromClient);
        model.addAttribute("flightsDTO", toClient);
        return "index";
    }

    @PostMapping
    public String filterFlights(@ModelAttribute FlightsDTO fromClient,
            Model model) {
        FlightsDTO toClient = service.filterFlights(fromClient);
        model.addAttribute("flightsDTO", toClient);
        return "index";
    }
}
