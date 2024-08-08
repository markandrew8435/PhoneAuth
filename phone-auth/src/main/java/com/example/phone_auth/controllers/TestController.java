package com.example.phone_auth.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hi")
    String getHi(){
        return "hi";
    }

    @GetMapping("/custom-hi")
    String getHi(@PathVariable String name){
        return "hi"+name;
    }

}
