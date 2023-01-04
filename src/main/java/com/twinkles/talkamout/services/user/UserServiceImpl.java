package com.twinkles.talkamout.services.user;

import com.twinkles.talkamout.dto.TherapistDto;
import com.twinkles.talkamout.dto.request.RegisterClientRequest;
import com.twinkles.talkamout.dto.request.RegisterTherapistRequest;
import com.twinkles.talkamout.exceptions.InvalidLicenceNumberException;
import com.twinkles.talkamout.exceptions.TalkAmOutException;
import com.twinkles.talkamout.exceptions.UserAlreadyExistException;
import com.twinkles.talkamout.model.Therapist;
import com.twinkles.talkamout.repository.UserRepository;
import com.twinkles.talkamout.utils.validators.TalkAmOutValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final TalkAmOutValidator talkAmOutValidator;

    @Override
    public TherapistDto registerTherapist(RegisterTherapistRequest registerTherapistRequest) {
        if(userRepository.existsByEmail(registerTherapistRequest.getEmail())){
            throw new UserAlreadyExistException("Therapist with email "+registerTherapistRequest.getEmail()+" already exist", 404);
        }
        else if (!talkAmOutValidator.validateLicenceNumber(registerTherapistRequest.getLicenceNumber())){
            throw new InvalidLicenceNumberException("Oops, your license number is not valid", 400);
        }
        Therapist therapist = new Therapist();
        BeanUtils.copyProperties(registerTherapistRequest, therapist);
        Therapist savedTherapist = userRepository.save(therapist);
        TherapistDto therapistDto = new TherapistDto();
        BeanUtils.copyProperties(savedTherapist, therapistDto);
        return therapistDto;
    }
}
