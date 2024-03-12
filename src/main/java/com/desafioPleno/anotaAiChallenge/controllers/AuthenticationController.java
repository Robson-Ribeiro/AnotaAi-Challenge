package com.desafioPleno.anotaAiChallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioPleno.anotaAiChallenge.security.AuthenticationService;
import com.desafioPleno.anotaAiChallenge.security.LoginDto;

import jakarta.validation.Valid;


@RestController
@RequestMapping("auth")
@CrossOrigin
public class AuthenticationController {
  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping
  public ResponseEntity<String> authenticate(@RequestBody @Valid LoginDto loginDto) {
    return ResponseEntity.ok().body(authenticationService.authenticate(loginDto));
  }
}
