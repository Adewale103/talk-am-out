package com.twinkles.talkamout.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterClientRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean hasTherapyHistory;
}
