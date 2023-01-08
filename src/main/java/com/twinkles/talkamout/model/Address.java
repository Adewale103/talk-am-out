package com.twinkles.talkamout.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

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
    private int postalCode;
    private String country;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Address(String streetNumber, String streetName, String city, String state, int postalCode, String country) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

}
