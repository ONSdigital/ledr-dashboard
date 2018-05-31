package uk.gov.ons.lerp.poc.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.gov.ons.lerp.poc.representation.DashboardData;
import uk.gov.ons.lerp.poc.respository.DataRepository;
import uk.gov.ons.lerp.poc.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {
@Autowired
  private DataRepository dataRepository;
   
  private ObjectMapper mapper = new ObjectMapper();

  //@Scheduled(cron="0 0 6-20 * * *")
  @Scheduled(fixedRate=5000)
  public void callSqlStatments(){  
	  findBirthsDashboardData("current_week");
	  findBirthsDashboardData("last_week");
	  findBirthsDashboardData("week_before");
	  findDeathsDashboardData("current_week");
	  findDeathsDashboardData("last_week");
	  findDeathsDashboardData("week_before");
  }
  
  
  
  @Override
  public void findBirthsDashboardData(String week) {
    
	List<Date> dates = findWeekRange(week);
	  
	DashboardData dd = new DashboardData();
    dd.setRecordsReceived(dataRepository.getBirthsRecordsReceived(dates.get(0),dates.get(1)));
    dd.setFullyCoded(dataRepository.getBirthsFullyCoded(dates.get(0),dates.get(1)));
    dd.setOutstandingGeographyFull(dataRepository.getBirthsOutstandingGeographyFull(dates.get(0),dates.get(1)));
    dd.setOutstandingGeographyPOB(dataRepository.getBirthsOutstandingGeographyPOB(dates.get(0),dates.get(1)));
    dd.setOutstandingGeographyPOE(dataRepository.getBirthsOutstandingGeographyPOE(dates.get(0),dates.get(1)));
    dd.setOutstandingGeographyUR(dataRepository.getBirthsOutstandingGeographyUR(dates.get(0),dates.get(1)));
    dd.setOutstandingOccupation(dataRepository.getBirthsOutstandingOccupation(dates.get(0),dates.get(1)));
    dd.setOutstandingCause(dataRepository.getBirthsOutstandingCause(dates.get(0),dates.get(1)));
    try{
    	mapper.writeValue(new File("D:\\BirthDashboardData"+week+".json"),dd);
    } catch (JsonGenerationException e) {
		e.printStackTrace();
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
    
  }
  
  @Override
  public void findDeathsDashboardData(String week) {
    
	  
	List<Date> dates = findWeekRange(week);
	  
	DashboardData dd = new DashboardData();
    dd.setRecordsReceived(dataRepository.getDeathsRecordsReceived(dates.get(0),dates.get(1)));
    dd.setFullyCoded(dataRepository.getDeathsFullyCoded(dates.get(0),dates.get(1)));
    dd.setOutstandingGeographyFull(dataRepository.getDeathsOutstandingGeographyFull(dates.get(0),dates.get(1)));
    dd.setOutstandingGeographyPOB(dataRepository.getDeathsOutstandingGeographyPOB(dates.get(0),dates.get(1)));
    dd.setOutstandingGeographyPOE(dataRepository.getDeathsOutstandingGeographyPOE(dates.get(0),dates.get(1)));
    dd.setOutstandingGeographyUR(dataRepository.getDeathsOutstandingGeographyUR(dates.get(0),dates.get(1)));
    dd.setOutstandingOccupation(dataRepository.getDeathsOutstandingOccupation(dates.get(0),dates.get(1)));
    dd.setOutstandingCause(dataRepository.getDeathsOutstandingCause(dates.get(0),dates.get(1)));
    
    try{
        mapper.writeValue(new File("D:\\DeathDashboardData"+week+".json"),dd);
    } catch (JsonGenerationException e) {
		e.printStackTrace();
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
    

  }
  
  
  @SuppressWarnings("static-access")
  public List<Date> findWeekRange(String week){
	
	List<Date> dates = new ArrayList<Date>();
	  
	int dayDiff = 0;
	switch (week){
	case "current_week":
	  dayDiff = 0;
	  break;
	case "last_week":
	  dayDiff = 7;
	  break;
	case "week_before":
      dayDiff = 14;
	  break;
	  
    }
	
	Calendar dateCurrentSat = Calendar.getInstance();
	dateCurrentSat.add(Calendar.DAY_OF_WEEK, -(dateCurrentSat.get(Calendar.DAY_OF_WEEK)+dayDiff));
	
	Calendar dateCurrentFri = Calendar.getInstance();
	dateCurrentFri.add(Calendar.DAY_OF_WEEK, -(dateCurrentFri.get(Calendar.DAY_OF_WEEK)-6+dayDiff));
	

    SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-YY");
    Date dateSat = dateCurrentSat.getTime();
    dateFormat.format(dateSat); 
    
    Date dateFri = dateCurrentFri.getTime();
    dateFormat.format(dateFri); 
    
    dates.add(dateSat);
    dates.add(dateFri);
	return dates;  
  }
}
