package uk.gov.ons.lerp.poc.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ons.lerp.poc.domain.RecordSummary;
import uk.gov.ons.lerp.poc.exception.CannotRetrieveDashboardData;
import uk.gov.ons.lerp.poc.service.DashboardService;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DashboardEndpoint {

  @Autowired
  private DashboardService service;

  @RequestMapping(value = "/ledr-dashboard-poc/topic/birth/{period}", method = RequestMethod.GET)
  public ResponseEntity<RecordSummary> getBirthDashboardData(@PathVariable("period") final String period,
                                                             HttpServletResponse response) throws CannotRetrieveDashboardData {

    response.setHeader("Access-Control-Allow-Origin", "*");
    return ResponseEntity.ok(service.retrieveBirthDashboardData(period));
  }

  @RequestMapping(value = "/ledr-dashboard-poc/topic/death/{period}", method = RequestMethod.GET)
  public ResponseEntity<RecordSummary> getDeathDashboardData(@PathVariable("period") final String period,
                                                             HttpServletResponse response) throws CannotRetrieveDashboardData {

    response.setHeader("Access-Control-Allow-Origin", "*");
    return ResponseEntity.ok(service.retrieveDeathDashboardData(period));
  }

  @RequestMapping(value = "/ledr-dashboard-poc/topic/death/{period}/occupation", method = RequestMethod.GET)
  public ResponseEntity<RecordSummary> getDeathOccupationData(@PathVariable("period") final String period,
                                                              HttpServletResponse response) throws CannotRetrieveDashboardData {

    response.setHeader("Access-Control-Allow-Origin", "*");
    return ResponseEntity.ok(service.retrieveDeathOccupationData(period));
  }
}
