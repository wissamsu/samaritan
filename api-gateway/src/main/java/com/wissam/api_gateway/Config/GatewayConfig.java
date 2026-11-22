package com.wissam.api_gateway.Config;

import java.net.InetSocketAddress;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

  @Bean
  KeyResolver ipKeyResolver() {
    return exchange -> {
      InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
      if (remoteAddress == null || remoteAddress.getAddress() == null) {
        return Mono.just("unknown");
      }
      return Mono.just(remoteAddress.getAddress().getHostAddress());
    };
  }

}
