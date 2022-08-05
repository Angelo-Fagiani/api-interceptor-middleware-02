package it.develhope.apiinterceptormiddleware02.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/echo")
public class BasicController {

    @GetMapping("")
    public String sayWelcome(){
        return "Welcome to my page!";
    }
}
