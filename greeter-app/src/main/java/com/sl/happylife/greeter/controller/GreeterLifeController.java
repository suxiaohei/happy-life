package com.sl.happylife.greeter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreeterLifeController {

    @GetMapping(path = "/greeter_life_test")
    public String testLife() {

        return "greeter/test";
    }
}
