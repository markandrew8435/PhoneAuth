package com.example.phone_auth.service;

import com.example.phone_auth.entities.OtpEntity;
import com.example.phone_auth.repository.OtpRepository;
import com.example.phone_auth.utils.OTPUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;


    public String generateOtp(String phoneNumber) {
        phoneNumber = phoneNumber.trim();
        String otp = OTPUtils.generateOTP();
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setPhoneNumber(phoneNumber);
        otpEntity.setHashCode(OTPUtils.computeSHA256(otp));
        otpEntity.setCreatedAt(LocalDateTime.now());
        otpRepository.save(otpEntity);
        sendOtp(phoneNumber, otp);
        return otp;
    }

    public boolean validateOtp(String phoneNumber, String otp) {
        OtpEntity otpEntity = otpRepository.findById(phoneNumber).orElse(null);
        if(otp.trim().length()==0)  return false;

        if (otpEntity == null || !otpEntity.getHashCode().equals(OTPUtils.computeSHA256(otp))) {
            return false;
        }
        Boolean result =  otpEntity.getCreatedAt().isAfter(LocalDateTime.now().minusMinutes(5));

        if(!result) return false;

        otpRepository.delete(otpEntity);
        return true;
    }

    private void sendOtp(String phoneNumber, String otp) {
//        send otp via sms
    }

    // Scheduled cleanup task
    @Scheduled(fixedRate = 60000) // Run every minute
    public void cleanupExpiredOtps() {
        otpRepository.deleteAll(otpRepository.findByCreatedAtBefore(LocalDateTime.now().minusMinutes(5)));
    }
}
