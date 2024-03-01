package com.desafioPleno.anotaAiChallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioPleno.anotaAiChallenge.security.AuthenticationService;


@RestController
public class AuthenticationController {
  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("authenticate")
  public ResponseEntity<String> authenticate(Authentication authentication) {
    return ResponseEntity.ok().body(authenticationService.authenticate(authentication));
  }
}
