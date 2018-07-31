package uk.gov.ons.lerp.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The main entry point into the LEDR Dashboard Application.
 *
 */
@ComponentScan(basePackages ={"uk.gov.ons.lerp.poc"})
@EnableJpaRepositories(basePackages = {"uk.gov.ons.lerp.poc"})
@EnableScheduling
@SpringBootApplication
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
  
  /**
   * Bean for reading and writing JSON
   * 
   */
  @Bean
  public ObjectMapper jsonMapper() {
	  return new ObjectMapper();
  }
}
