package com.wissam.user_service.Events.User;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wissam.user_service.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * UserSaver
 */
@Component
@RequiredArgsConstructor
public class UserSaver extends OncePerRequestFilter {

  private final UserRepository userRepository;

}
