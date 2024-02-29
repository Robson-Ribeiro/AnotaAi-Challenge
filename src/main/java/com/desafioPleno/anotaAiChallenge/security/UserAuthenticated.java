package com.desafioPleno.anotaAiChallenge.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.desafioPleno.anotaAiChallenge.domain.User.UserEntity;


public class UserAuthenticated implements UserDetails {
  private final UserEntity userEntity;

  public UserAuthenticated(UserEntity userEntity) {
    this.userEntity = userEntity;
  }

  @Override
  public String getUsername() {
    return userEntity.getUsername();
  }

  @Override
  public String getPassword() {
    return userEntity.getPassword();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(() -> "seller");
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
