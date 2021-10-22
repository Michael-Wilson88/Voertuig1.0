package com.example.Voertuig.controller;

import com.example.Voertuig.payload.request.LoginRequest;
import com.example.Voertuig.payload.request.SignupRequest;
import com.example.Voertuig.payload.response.JwtResponse;
import com.example.Voertuig.payload.response.MessageResponse;
import com.example.Voertuig.serviceImpl.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthorizationService authorizationService;

    Logger logger = Logger.getLogger(AuthController.class.getName());

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        logger.info("User log in: " + loginRequest.getUsername());
        return authorizationService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signUpRequest) {
        logger.info("User sign in: " + signUpRequest.getUsername());
        return authorizationService.registerUser(signUpRequest);
    }

}