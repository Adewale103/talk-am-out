package com.twinkles.talkamout.services.user;

import com.twinkles.talkamout.dto.TherapistDto;
import com.twinkles.talkamout.dto.request.RegisterTherapistRequest;
import com.twinkles.talkamout.model.Therapist;
import com.twinkles.talkamout.model.User;
import com.twinkles.talkamout.repository.AddressRepository;
import com.twinkles.talkamout.repository.QuestionRepository;
import com.twinkles.talkamout.repository.TherapistRepository;
import com.twinkles.talkamout.repository.UserRepository;
import com.twinkles.talkamout.utils.validators.TalkAmOutValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TalkAmOutValidator talkAmOutValidator;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private TherapistRepository therapistRepository;
    @Mock
    private AddressRepository addressRepository;
    private UserService userService;
    private RegisterTherapistRequest registerTherapistRequest;
    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository,talkAmOutValidator,questionRepository,therapistRepository,addressRepository);
        registerTherapistRequest = RegisterTherapistRequest.builder()
                .email("james@gmail.com")
                .licenceNumber("NG123446765")
                .yearsOfExperience(4)
                .firstName("James")
                .lastName("Andrew")
                .password("Capatapata")
                .build();
    }

    @Test
    public void therapistCanRegisterTest(){
        Therapist therapist = new Therapist();
        BeanUtils.copyProperties(registerTherapistRequest, therapist);
        therapist.setId(1L);
        when(userRepository.existsByEmail("james@gmail.com")).thenReturn(false);
        when(talkAmOutValidator.validateLicenceNumber("NG123446765")).thenReturn(true);
        when(userRepository.save(any(Therapist.class))).thenReturn(therapist);

        TherapistDto therapistDto = userService.registerTherapist(registerTherapistRequest);
        verify(userRepository, times(1)).existsByEmail("james@gmail.com");
        assertThat(therapistDto.getFirstName()).isEqualTo("James");
        assertThat(therapistDto.getLastName()).isEqualTo("Andrew");
    }
}