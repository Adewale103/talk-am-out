package com.twinkles.talkamout.services.user;

import com.twinkles.talkamout.dto.AnswerDto;
import com.twinkles.talkamout.dto.ClientDto;
import com.twinkles.talkamout.dto.TherapistDto;
import com.twinkles.talkamout.dto.request.CompleteTherapistProfileRequest;
import com.twinkles.talkamout.dto.request.RegisterClientRequest;
import com.twinkles.talkamout.dto.request.RegisterTherapistRequest;
import com.twinkles.talkamout.dto.response.ViewTherapistProfileResponse;
import com.twinkles.talkamout.enums.Role;
import com.twinkles.talkamout.exceptions.InvalidLicenceNumberException;
import com.twinkles.talkamout.exceptions.TalkAmOutException;
import com.twinkles.talkamout.exceptions.UserAlreadyExistException;
import com.twinkles.talkamout.model.Answer;
import com.twinkles.talkamout.model.AppClient;
import com.twinkles.talkamout.model.Therapist;
import com.twinkles.talkamout.model.User;
import com.twinkles.talkamout.repository.QuestionRepository;
import com.twinkles.talkamout.repository.TherapistRepository;
import com.twinkles.talkamout.repository.UserRepository;
import com.twinkles.talkamout.utils.validators.TalkAmOutValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final TalkAmOutValidator talkAmOutValidator;
    private final QuestionRepository questionRepository;
    private final TherapistRepository therapistRepository;

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
        therapist.setRole(Role.THERAPIST);
        Therapist savedTherapist = userRepository.save(therapist);
        return getTherapistDtoFrom(savedTherapist);
    }

    private static TherapistDto getTherapistDtoFrom(Therapist savedTherapist) {
        return TherapistDto.builder()
                .id(savedTherapist.getId())
                .firstName(savedTherapist.getFirstName())
                .lastName(savedTherapist.getLastName())
                .licenceNumber(savedTherapist.getLicenceNumber())
                .email(savedTherapist.getEmail())
                .yearsOfExperience(savedTherapist.getYearsOfExperience())
                .build();
    }

    @Override
    public ClientDto registerClient(RegisterClientRequest registerClientRequest) {
        List<AnswerDto> answerDtoList = registerClientRequest.getAnswerDtoList();
        if(userRepository.existsByEmail(registerClientRequest.getEmail())){
            throw new UserAlreadyExistException("Client with email "+registerClientRequest.getEmail()+" already exist", 400);
        }
        AppClient client = new AppClient();
        BeanUtils.copyProperties(registerClientRequest, client);
        List<Answer> answers = answerDtoList.stream().map(answerDto -> Answer.builder()
                .user(client)
                .response(answerDto.getResponse())
                .question(questionRepository.findQuestionByQuestionNumber(answerDto.getQuestionNumber()).
                        orElseThrow(()-> new TalkAmOutException("Question with question number "+answerDto.getQuestionNumber()+" not found",404)))
                .build()).toList();
        client.setQuestionResponses(answers);
        client.setRole(Role.CLIENT);
        AppClient savedClient = userRepository.save(client);
        return ClientDto.builder()
                .id(savedClient.getId())
                .email(savedClient.getEmail())
                .firstName(savedClient.getFirstName())
                .lastName(savedClient.getLastName())
                .build();
    }

    @Override
    public ViewTherapistProfileResponse viewTherapistProfile(String email) {
        Optional<User> foundTherapist = userRepository.findByEmail(email);
        if(foundTherapist.isEmpty()){
            throw new UserAlreadyExistException("Therapist with email "+email+" not found", 404);
        }
        if(!foundTherapist.get().getRole().equals(Role.THERAPIST)){
            throw new TalkAmOutException("Email provided is not for a therapist profile", 404);
        }
        ViewTherapistProfileResponse viewTherapistProfileResponse = new ViewTherapistProfileResponse();
        BeanUtils.copyProperties(foundTherapist.get(), viewTherapistProfileResponse);
        return viewTherapistProfileResponse;
    }

    @Override
    public TherapistDto completeTherapistProfile(String email, CompleteTherapistProfileRequest completeTherapistProfileRequest) {
        Optional<User> foundTherapist = userRepository.findByEmail(email);
        if(foundTherapist.isEmpty()){
            throw new UserAlreadyExistException("Therapist with email "+email+" not found", 404);
        }
        Therapist therapist = (Therapist) foundTherapist.get();
        BeanUtils.copyProperties(completeTherapistProfileRequest, therapist);
        userRepository.save(therapist);
        return  TherapistDto.builder()
                .id(therapist.getId())
                .firstName(therapist.getFirstName())
                .lastName(therapist.getLastName())
                .yearsOfExperience(therapist.getYearsOfExperience())
                .licenceNumber(therapist.getLicenceNumber())
                .build();
    }

    @Override
    public List<TherapistDto> findTherapistByLocation(String location) {
       List<Therapist> users = therapistRepository.findTherapistByAddress_State(location);
        return users.stream().filter(user -> user.getRole().equals(Role.THERAPIST)).map(user ->
                TherapistDto.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .yearsOfExperience(user.getYearsOfExperience())
                        .licenceNumber(user.getLicenceNumber())
                        .build()
        ).toList();
    }

}
