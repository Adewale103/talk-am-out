package com.twinkles.talkamout.utils.validators;

import org.springframework.stereotype.Component;

@Component
public class MockValidator implements TalkAmOutValidator {
    @Override
    public boolean validateLicenceNumber(String licenceNumber) {
        return licenceNumber.length() > 5 && licenceNumber.contains("N");
    }
}
