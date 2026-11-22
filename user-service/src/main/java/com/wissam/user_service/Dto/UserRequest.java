package com.wissam.user_service.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * UserRequest
 */
public record UserRequest(
    @NotBlank @Email String email) {
}
