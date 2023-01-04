package com.twinkles.talkamout.model;

import com.twinkles.talkamout.enums.LicenseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "therapist")
public class Therapist extends User{
    private String licenceNumber;
    private String professionalStatement;
    private String messageToClients;
    private String specialty;
    private String treatmentApproaches;
    private int yearsOfExperience;
    @Enumerated(EnumType.STRING)
    private LicenseType licenseType;
    private boolean isAvailable;
}
