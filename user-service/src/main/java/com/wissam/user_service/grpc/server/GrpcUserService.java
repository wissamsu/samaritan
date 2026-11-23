package com.wissam.user_service.grpc.server;

import org.springframework.grpc.server.service.GrpcService;

import com.wissam.user_service.Service.UserService;
import com.wissam.user_service.grpc.CreateUserRequest;
import com.wissam.user_service.grpc.CreateUserResponse;
import com.wissam.user_service.grpc.UserServiceGrpc.UserServiceImplBase;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

/**
 * GrpcUserService
 */
@GrpcService
@RequiredArgsConstructor
public class GrpcUserService extends UserServiceImplBase {

  private final UserService userService;

  @Override
  public void createUser(CreateUserRequest request, StreamObserver<CreateUserResponse> response) {

  }

}
