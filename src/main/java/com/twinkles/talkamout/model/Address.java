package com.twinkles.talkamout.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address extends BaseClass {
    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    private Integer postalCode;
    private String country;
}
