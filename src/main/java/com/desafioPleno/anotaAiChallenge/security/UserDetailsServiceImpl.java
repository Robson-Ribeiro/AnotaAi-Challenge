package com.desafioPleno.anotaAiChallenge.security;

import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.desafioPleno.anotaAiChallenge.domain.User.UserEntity;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final MongoTemplate mongoTemplate;

  public UserDetailsServiceImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Query query = new Query();
    query.addCriteria(Criteria.where("username").is(username));
    List<UserEntity> user = mongoTemplate.find(query, UserEntity.class);
    try {
        return new UserAuthenticated(user.get(0));
    } catch (Exception e) {
        throw new UsernameNotFoundException("User Not Found with username: " + username);
    }
  }
}
