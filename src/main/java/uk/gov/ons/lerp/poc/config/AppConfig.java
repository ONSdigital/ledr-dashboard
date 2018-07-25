package uk.gov.ons.lerp.poc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties
@Data
public class AppConfig {
  
  private Ldap ldap;
  private QuerySchedule querySchedule;
  private FileLocation fileLocation;
  
  @Data
  public static class Ldap {
      
    private String domain;
    private String url;
    private String rootdn;
    private String searchfilter;
	}

  @Data
  public static class QuerySchedule {

    private String weeklyCron;
    private String monthlyCron;
    private String quarterlyCron;
    private String yearlyCron;
  }

  @Data
  public static class FileLocation {

    private String birth;
    private String death;
  } 
  
}
