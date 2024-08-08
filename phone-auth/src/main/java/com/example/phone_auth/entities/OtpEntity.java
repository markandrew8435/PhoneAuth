package com.example.phone_auth.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class OtpEntity extends BaseEntity{
    @Id
    private String phoneNumber;
    private String hashCode;

}
