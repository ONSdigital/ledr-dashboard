package uk.gov.ons.lerp.poc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/** Application Config bean */
@Configuration
@ConfigurationProperties
@Data
public class AppConfig {
  private LDAP ldap;
}