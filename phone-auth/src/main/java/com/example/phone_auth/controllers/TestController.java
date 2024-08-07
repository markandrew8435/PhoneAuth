package com.example.phone_auth.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hi")
    String getHi(){
        return "hi";
    }

}
