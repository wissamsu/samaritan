package com.wissam.api_gateway.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FallbackController
 */
@RestController
public class FallbackController {

  @RequestMapping("/fallback/user-service")
  public ResponseEntity<String> fallbackCB() {
    return ResponseEntity.status(503).body("User Service is currently unavailable. Please try again later");
  }

}
