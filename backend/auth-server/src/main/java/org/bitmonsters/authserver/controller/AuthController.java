package org.bitmonsters.authserver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bitmonsters.authserver.dto.*;
import org.bitmonsters.authserver.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(
            @Valid @RequestBody UserRegistrationRequest userRegistrationRequest
            ) {
        authService.registerUser(userRegistrationRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(
            @Valid @RequestBody AuthenticationRequest authenticationRequest
            ) {
        // handle the user authentication login
        return null;
    }

    @GetMapping("/register/email/confirm")
    @ResponseStatus(HttpStatus.OK)
    public IDResponse confirmUserRegistration(
            @RequestParam("email") String email,
            @RequestParam("code") String code
    ) {
        return authService.confirmEmail(email, code);
    }

    @PostMapping("/password/reset")
    @ResponseStatus(HttpStatus.OK)
    public void sendResetPasswordLink(
            @Valid @RequestBody PasswordResetRequest passwordResetRequest
    ) {
        // send the password reset link to the email

    }

    @GetMapping("/password/reset")
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(
            @RequestParam("email") String email,
            @RequestParam("code") String code
    ) {
        // reset the password
    }

}
