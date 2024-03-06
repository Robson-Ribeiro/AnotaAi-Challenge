package com.desafioPleno.anotaAiChallenge.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.desafioPleno.anotaAiChallenge.domain.User.UserDto;

@Service
public class AuthenticationService {
  private JwtService jwtService;

  private AuthenticationManager authenticationManager;

  public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager) {
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  public String authenticate(UserDto data) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
    Authentication auth = this.authenticationManager.authenticate(usernamePassword);
    return jwtService.generateToken(auth);
  }
}
