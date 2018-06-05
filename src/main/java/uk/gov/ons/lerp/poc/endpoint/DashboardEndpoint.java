package uk.gov.ons.lerp.poc.endpoint;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uk.gov.ons.lerp.poc.service.impl.DashboardServiceImpl;
import uk.gov.ons.lerp.poc.representation.DashboardData;

@RestController
public class DashboardEndpoint {
  
  @Value("${file.directory.birth}")
  private String fileLoactionBirth;
  
  @Value("${file.directory.death}")
  private String fileLoactionDeath;
  
  DashboardServiceImpl service;
  
  @RequestMapping(value = "/ledr-dashboard-poc/topic/{topic}/{period}", method = RequestMethod.GET)
  public ResponseEntity<DashboardData> getDashboardData(@PathVariable("topic") final String topic,
                                               @PathVariable("period") final String period, HttpServletResponse response){

    response.setHeader("Access-Control-Allow-Origin","*");
    return ResponseEntity.ok(service.retrieveDashboardData(topic,period));

  }
	
}
