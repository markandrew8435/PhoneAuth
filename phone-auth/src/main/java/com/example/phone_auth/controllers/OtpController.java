package com.example.phone_auth.controllers;

import com.example.phone_auth.exception_hander.ApiException;
import com.example.phone_auth.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/get")
    public String getOtp(@RequestParam("phone_number") String phoneNumber) throws ApiException {
        return otpService.generateOtp(phoneNumber);
    }

    @GetMapping("/validate")
    public boolean validateOtp(@RequestParam("phone_number") String phoneNumber, @RequestParam("otp") String otp) throws ApiException {
        return otpService.validateOtp(phoneNumber, otp);
    }
}
