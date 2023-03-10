package com.twinkles.talkamout.services.user;


import com.twinkles.talkamout.dto.ClientDto;
import com.twinkles.talkamout.dto.TherapistDto;
import com.twinkles.talkamout.dto.request.CompleteTherapistProfileRequest;
import com.twinkles.talkamout.dto.request.RegisterClientRequest;
import com.twinkles.talkamout.dto.request.RegisterTherapistRequest;
import com.twinkles.talkamout.dto.AnswerDto;
import com.twinkles.talkamout.dto.response.ViewTherapistProfileResponse;

import java.util.List;

public interface UserService {

 TherapistDto registerTherapist(RegisterTherapistRequest registerTherapistRequest);
 ClientDto registerClient(RegisterClientRequest registerClientRequest);
 ViewTherapistProfileResponse viewTherapistProfile(String email);
 TherapistDto completeTherapistProfile(String email, CompleteTherapistProfileRequest completeTherapistProfileRequest);
 List<TherapistDto> findTherapistByLocation(String Location);
}
