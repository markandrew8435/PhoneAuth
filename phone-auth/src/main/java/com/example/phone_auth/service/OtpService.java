package com.example.phone_auth.service;

import com.example.phone_auth.entities.OtpEntity;
import com.example.phone_auth.exception_hander.ApiException;
import com.example.phone_auth.repository.OtpRepository;
import com.example.phone_auth.utils.OTPUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;


    public String generateOtp(String phoneNumber) throws ApiException {
        phoneNumber = phoneNumber.trim();

        OtpEntity otpEntity = otpRepository.findByPhoneNumber(phoneNumber);
        if(Objects.nonNull(otpEntity) && otpEntity.getUpdatedAt().isAfter(LocalDateTime.now().minusMinutes(OTPUtils.regenerationTimeThreshold)))
            throw new ApiException("Otp cannot be generated again within %d minute(s) for the same phone number"
                    .formatted(OTPUtils.regenerationTimeThreshold));

        if(Objects.isNull(otpEntity)) otpEntity = new OtpEntity();

        String otp = OTPUtils.generateOTP();
        otpEntity.setPhoneNumber(phoneNumber);
        otpEntity.setHashCode(OTPUtils.computeSHA256(otp));
//        otpRepository.save(otpEntity);
        sendOtp(phoneNumber, otp);
        return otp;
    }

    public boolean validateOtp(String phoneNumber, String otp) throws ApiException {
        phoneNumber = phoneNumber.trim();
        OtpEntity otpEntity = otpRepository.findById(phoneNumber).orElse(null);
        if(otp.trim().length()==0)  return false;

        if (otpEntity == null) throw new ApiException("otp is either expired or not generated for the given phoneNumber");

        if(!otpEntity.getHashCode().equals(OTPUtils.computeSHA256(otp))) return false;

        boolean result =  otpEntity.getCreatedAt().isAfter(LocalDateTime.now().minusMinutes(5));

        if(!result) return false;

        otpRepository.delete(otpEntity);
        return true;
    }

    private void sendOtp(String phoneNumber, String otp) {
//        send otp via sms
    }

    // Scheduled cleanup task
    @Scheduled(fixedRate = 5*60000) // Run every 5 minute
    public void cleanupExpiredOtps() {
        otpRepository.deleteAll(otpRepository.findByCreatedAtBefore(LocalDateTime.now().minusMinutes(5)));
    }
}
