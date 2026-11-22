package com.wissam.user_service.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * kafkaConfig
 */
@Configuration
public class kafkaConfig {

  @Bean
  NewTopic userTopic() {
    return new NewTopic("userTopic", 3, (short) 1);
  }

}
