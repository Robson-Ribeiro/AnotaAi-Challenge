package com.desafioPleno.anotaAiChallenge.config.SecurityConfig;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.desafioPleno.anotaAiChallenge.domain.User.UserEntity;
import com.desafioPleno.anotaAiChallenge.security.UserAuthenticated;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilterConfig extends OncePerRequestFilter {
    @Autowired
    JwtDecoder JwtDecoder;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null) {
            var login = JwtDecoder.decode(token);
            Query query = new Query();
            query.addCriteria(Criteria.where("username").is(login.getSubject()));
            List<UserEntity> user = mongoTemplate.find(query, UserEntity.class);
            var userAuthenticated = new UserAuthenticated(user.get(0));
            var authentication = new UsernamePasswordAuthenticationToken(user.get(0).getUsername(), null, userAuthenticated.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
