package com.twinkles.talkamout.services.user;


import com.twinkles.talkamout.dto.TherapistDto;
import com.twinkles.talkamout.dto.request.RegisterTherapistRequest;

public interface UserService {

 TherapistDto registerTherapist(RegisterTherapistRequest registerTherapistRequest);
}
