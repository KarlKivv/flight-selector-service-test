package com.flightSelectorDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TmpController {
    @RequestMapping("/tmp")
    public String tmp() {
        return "tmp";
    }

}
