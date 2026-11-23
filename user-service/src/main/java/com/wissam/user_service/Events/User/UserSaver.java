package com.wissam.user_service.Events.User;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
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
@Component
@RequiredArgsConstructor
public class UserSaver extends OncePerRequestFilter {

  private final UserRepository userRepository;

  @Override
  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (SecurityContextHolder.getContext().getAuthentication() instanceof JwtAuthenticationToken auth) {
      String email = auth.getToken().getClaimAsString("email");
      if (email != null) {
        email = email.toLowerCase();
        if (!userRepository.existsByEmail(email)) {
          User user = new User();
          user.setEmail(email);
          userRepository.save(user);
        }
      }
    }
    filterChain.doFilter(request, response);

  }

}
