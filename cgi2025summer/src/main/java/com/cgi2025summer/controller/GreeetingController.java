package com.cgi2025summer.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgi2025summer.dto.GreetingRecord;

@RestController
public class GreeetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public GreetingRecord greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new GreetingRecord(counter.incrementAndGet(), String.format(template, name));
    }
}
