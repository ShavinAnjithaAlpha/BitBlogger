package org.bitmonsters.authserver.controller;

import jakarta.validation.Valid;
import org.bitmonsters.authserver.dto.AuthenticationRequest;
import org.bitmonsters.authserver.dto.AuthenticationResponse;
import org.bitmonsters.authserver.dto.IDResponse;
import org.bitmonsters.authserver.dto.UserRegistrationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(
            @Valid @RequestBody UserRegistrationRequest userRegistrationRequest
            ) {
        // user registration logic
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(
            @Valid @RequestBody AuthenticationRequest authenticationRequest
            ) {
        // handle the user authentication login
        return null;
    }


}
