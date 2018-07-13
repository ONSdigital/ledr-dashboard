package uk.gov.ons.lerp.poc.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class LDAPConfig {

  @Value("${ldap.domain}")
  private String domain;

  @Value("${ldap.url}")
  private String url;

  @Value("${ldap.rootdn}")
  private String rootdn;

  @Value("${ldap.searchfilter}")
  private String searchfilter;

}
