package com.twinkles.talkamout.controllers;

import com.twinkles.talkamout.dto.ClientDto;
import com.twinkles.talkamout.dto.TherapistDto;
import com.twinkles.talkamout.dto.request.RegisterClientRequest;
import com.twinkles.talkamout.dto.request.RegisterTherapistRequest;
import com.twinkles.talkamout.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/register/therapist")
    public ResponseEntity<?> registerTherapist(@RequestBody RegisterTherapistRequest registerTherapistRequest){
        TherapistDto therapistDto = userService.registerTherapist(registerTherapistRequest);
        return new ResponseEntity<>(therapistDto, HttpStatus.CREATED);
    }
    @PostMapping("/register/client")
    public ResponseEntity<?> registerClient(@RequestBody RegisterClientRequest registerClientRequest){
        ClientDto clientDto = userService.registerClient(registerClientRequest);
        return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
    }
    @PostMapping("/{location}")
    public ResponseEntity<?> findTherapistByLocation(@PathVariable String location){
        List<TherapistDto> therapistDtoList = userService.findTherapistByLocation(location);
        return new ResponseEntity<>(therapistDtoList, HttpStatus.CREATED);
    }
}
