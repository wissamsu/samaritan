package com.wissam.user_service.Service;

import org.springframework.stereotype.Service;

import com.wissam.user_service.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * UserService
 */
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

}
