package com.wissam.user_service.Events.User;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wissam.user_service.Models.User;
import com.wissam.user_service.Repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * UserSaver
 */
@RequiredArgsConstructor
public class UserSaver extends OncePerRequestFilter {

  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth instanceof JwtAuthenticationToken token) {
      Jwt jwt = token.getToken();
      String email = jwt.getClaimAsString("email");
      if (email != null && !userRepository.existsByEmail(email)) {
        User user = User.builder().email(email).build();
        userRepository.save(user);
      }
    }
    filterChain.doFilter(request, response);
  }

}
