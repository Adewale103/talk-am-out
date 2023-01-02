package com.twinkles.talkamout.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Address extends BaseClass {
    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    private Integer postalCode;
    private String country;
}
