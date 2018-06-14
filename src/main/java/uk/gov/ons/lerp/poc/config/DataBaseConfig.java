package uk.gov.ons.lerp.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import uk.gov.ons.lerp.poc.domain.FileLocation;

@Configuration
public class DataBaseConfig {
  
  @Bean
  public FileLocation fileLocation(){
    return FileLocation.builder().fileLocationBirth("${file.directory.birth}").fileLocationDeath("${file.directory.death}").build();
  } 
}
