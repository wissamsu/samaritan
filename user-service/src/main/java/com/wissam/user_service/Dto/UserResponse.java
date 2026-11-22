package com.wissam.user_service.Dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * UserResponse
 */

@JsonInclude(value = Include.NON_NULL)
public record UserResponse(
    UUID id,
    String email) {
}
