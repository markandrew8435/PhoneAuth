package com.example.phone_auth.repository;

import com.example.phone_auth.entities.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, String> {
    List<OtpEntity> findByCreatedAtBefore(LocalDateTime dateTime);

    OtpEntity findByPhoneNumber(String phoneNumber);
}