package com.twinkles.talkamout.services.user;

import com.twinkles.talkamout.dto.AnswerDto;
import com.twinkles.talkamout.dto.ClientDto;
import com.twinkles.talkamout.dto.TherapistDto;
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
        TherapistDto therapistDto = new TherapistDto();
        BeanUtils.copyProperties(savedTherapist, therapistDto);
        return therapistDto;
    }

    @Override
    public ClientDto registerClient(RegisterClientRequest registerClientRequest, List<AnswerDto> answerDtoList) {
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
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(savedClient,clientDto);
        return clientDto;
    }

    @Override
    public ViewTherapistProfileResponse viewTherapistProfile(String email) {
        Optional<User> foundTherapist = userRepository.findByEmail(email);
        if(foundTherapist.isEmpty()){
            throw new UserAlreadyExistException("Therapist with email "+email+" not found", 404);
        }
        ViewTherapistProfileResponse viewTherapistProfileResponse = new ViewTherapistProfileResponse();
        BeanUtils.copyProperties(foundTherapist.get(), viewTherapistProfileResponse);
        return viewTherapistProfileResponse;
    }

    @Override
    public List<TherapistDto> findTherapistByLocation(String location) {
        Optional<User> users = userRepository.findTherapistByAddress_State(location);
        return users.stream().filter(user -> user.getRole().equals(Role.THERAPIST)).map(user ->
                TherapistDto.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .yearsOfExperience(user.));
    }
}
