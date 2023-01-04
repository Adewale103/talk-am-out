package com.twinkles.talkamout.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TherapistDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String licenceNumber;
    private int yearsOfExperience;
}
