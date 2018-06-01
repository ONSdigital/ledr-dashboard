package uk.gov.ons.lerp.poc.endpoint;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.gov.ons.lerp.poc.representation.DashboardData;
import uk.gov.ons.lerp.poc.service.DashboardService;

@RestController
public class DashboardEndpoint {

  @Autowired
  private DashboardService dashBoardService;
  
  @RequestMapping(value = "/ledr-dashboard-poc/topic/{topic}/{period}", method = RequestMethod.GET)
  public ResponseEntity<DashboardData> queryDB(@PathVariable("topic") final String topic,
                                               @PathVariable("period") final String period, HttpServletResponse response){

	DashboardData dashboardData = new DashboardData();
	
	ObjectMapper mapper = new ObjectMapper();
	  
	switch (topic){
	case "births":
		try {
			dashboardData = mapper.readValue(new File("D:\\BirthDashboardData"+period+".json"), DashboardData.class);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
      break;
	case "deaths":
		try {
			dashboardData = mapper.readValue(new File("D:\\DeathDashboardData"+period+".json"), DashboardData.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  break;
	}
    response.setHeader("Access-Control-Allow-Origin","*");
    return ResponseEntity.ok(dashboardData);

  }
	
}
