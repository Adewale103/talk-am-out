package com.twinkles.talkamout.dto.response;

import com.twinkles.talkamout.enums.LicenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewTherapistProfileResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String licenceNumber;
    private String professionalStatement;
    private String messageToClients;
    private String specialty;
    private String treatmentApproaches;
    private int yearsOfExperience;
    private LicenseType licenseType;
    private boolean isAvailable;
    private String profileImageUrl;
}
