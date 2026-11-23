package com.wissam.user_service.Config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.server.GlobalServerInterceptor;
import org.springframework.grpc.server.security.AuthenticationProcessInterceptor;
import org.springframework.grpc.server.security.GrpcSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.wissam.user_service.Events.User.UserSaver;
import com.wissam.user_service.Repository.UserRepository;

/**
 * SecurityConfig
 */
@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http, UserRepository userRepository) {
    http
        .csrf(csrf -> csrf.disable())
        .cors(Customizer.withDefaults())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/hello", "/actuator/health").authenticated()
            .anyRequest().authenticated())
        .oauth2ResourceServer(oath2 -> oath2.jwt(Customizer.withDefaults()))
        .addFilterAfter(new UserSaver(userRepository), BearerTokenAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  @GlobalServerInterceptor
  AuthenticationProcessInterceptor jwtSecurityFilterChain(GrpcSecurity security, UserRepository userRepository)
      throws Exception {
    return security
        .authorizeRequests(auth -> auth
            .methods("user_service.v1.UserService/CreateUser").authenticated()
            .allRequests().authenticated())
        .oauth2ResourceServer(oath2 -> oath2.jwt(Customizer.withDefaults()))
        .build();
  }

  @Bean
  CorsConfigurationSource corsConfiguration() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedOrigins(List.of("*"));
    corsConfiguration.setAllowedMethods(List.of("*"));
    corsConfiguration.setAllowedHeaders(List.of("*"));
    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
    return urlBasedCorsConfigurationSource;
  }

}
