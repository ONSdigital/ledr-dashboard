package uk.gov.ons.lerp.poc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
@Data
public class LDAP {
  private String domain;
  private String url;
  private String rootdn;
  private String searchfilter;
}
