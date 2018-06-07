package uk.gov.ons.lerp.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import uk.gov.ons.lerp.poc.domain.FileLocation;

@Configuration
public class DataBaseConfig {
  
  @Bean
  public FileLocation fileLocation(){
    return FileLocation.builder().fileLoactionBirth("${file.directory.birth}").fileLoactionDeath("${file.directory.death}").build();
  } 
}
