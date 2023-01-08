package com.twinkles.talkamout.dto.request;

import com.twinkles.talkamout.enums.LicenseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompleteTherapistProfileRequest {
    private String professionalStatement;
    private String messageToClients;
    private String specialty;
    private String treatmentApproaches;
    private LicenseType licenseType;
    private boolean isAvailable;
    private String profileImageUrl;
    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    private int postalCode;
    private String country;
}
