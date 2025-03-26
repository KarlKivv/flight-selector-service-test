package com.cgi2025summer.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class TmpController {
    @RequestMapping("/tmp")
    public String tmp() {
        return "tmp";
    }

}
