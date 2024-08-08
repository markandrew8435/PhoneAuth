package com.example.phone_auth.entities;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
public class OtpEntity extends BaseEntity{
    @Id
    private String phoneNumber;
    private String hashCode;

}
