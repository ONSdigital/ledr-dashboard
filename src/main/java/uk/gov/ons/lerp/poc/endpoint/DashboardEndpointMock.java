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
public class DashboardEndpointMock {

  @Autowired
  private DashboardService service;

  @RequestMapping(value = "/ledr-dashboard-poc/test/topic/birth/{period}", method = RequestMethod.GET)
  public ResponseEntity<RecordSummary> getBirthDashboardData(@PathVariable("period") final String period,
                                                             HttpServletResponse response) throws CannotRetrieveDashboardData {
    RecordSummary mockRecordSummary = new RecordSummary();
    mockRecordSummary.setRecordsReceived(100);
    mockRecordSummary.setFullyCoded(75);
    mockRecordSummary.setOutstandingGeographyFull(20);
    mockRecordSummary.setOutstandingGeographyPOB(6);
    mockRecordSummary.setOutstandingGeographyPOE(4);
    mockRecordSummary.setOutstandingGeographyUR(10);
    mockRecordSummary.setOutstandingOccupation(13);
    mockRecordSummary.setOutstandingCause(22);
    response.setHeader("Access-Control-Allow-Origin", "*");
    return ResponseEntity.ok(mockRecordSummary);
  }

  @RequestMapping(value = "/ledr-dashboard-poc/test/topic/death/{period}", method = RequestMethod.GET)
  public ResponseEntity<RecordSummary> getDeathDashboardData(@PathVariable("period") final String period,
                                                             HttpServletResponse response) throws CannotRetrieveDashboardData {
    RecordSummary testRecordSummary = new RecordSummary();
    testRecordSummary.setRecordsReceived(96);
    testRecordSummary.setFullyCoded(40);
    testRecordSummary.setOutstandingGeographyFull(33);
    testRecordSummary.setOutstandingGeographyPOB(12);
    testRecordSummary.setOutstandingGeographyPOE(8);
    testRecordSummary.setOutstandingGeographyUR(7);
    testRecordSummary.setOutstandingOccupation(18);
    testRecordSummary.setOutstandingCause(26);
    response.setHeader("Access-Control-Allow-Origin", "*");
    return ResponseEntity.ok(testRecordSummary);
  }

  @RequestMapping(value = "/ledr-dashboard-poc/test/topic/death/{period}/occupation", method = RequestMethod.GET)
  public ResponseEntity<RecordSummary> getDeathOccupationData(@PathVariable("period") final String period,
                                                              HttpServletResponse response) throws CannotRetrieveDashboardData {

    response.setHeader("Access-Control-Allow-Origin", "*");
    return ResponseEntity.ok(service.retrieveDeathOccupationData(period));
  }
}
