package uk.gov.ons.lerp.poc.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.gov.ons.lerp.poc.representation.DashboardData;
import uk.gov.ons.lerp.poc.representation.TimePeriod;
import uk.gov.ons.lerp.poc.respository.DataRepository;
import uk.gov.ons.lerp.poc.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {
  
  Logger logger = Logger.getAnonymousLogger();  	

  @Autowired
  private DataRepository dataRepository;
  
  DashboardData dashboardData = new DashboardData();
  
  private ObjectMapper mapper = new ObjectMapper();

  @Value("${file.directory.birth}")
  private String fileLoactionBirth;
  
  @Value("${file.directory.death}")
  private String fileLoactionDeath;
  
  public DashboardData retrieveDashboardData(final String topic, final String period){
		
    try {
	  switch (topic){
	    case "births":
	      dashboardData = mapper.readValue(new File(fileLoactionBirth+period+".json"), DashboardData.class);
	      break;
	    case "deaths":
	      dashboardData = mapper.readValue(new File(fileLoactionDeath+period+".json"), DashboardData.class);
	      break;
	  }
	}catch (IOException e1) {
	  logger.log(Level.SEVERE,"could not retrieve file" ,e1);
	}
    return dashboardData;
  }
  
  
  
  //@Scheduled(cron="0 0 6-20 * * *")
  @Scheduled(fixedRate=5000)
  public void callWeeklySqlStatments(){  
	  findBirthsDashboardData(TimePeriod.WEEK_CURRENT);
	  findBirthsDashboardData(TimePeriod.WEEK_LAST);
	  findBirthsDashboardData(TimePeriod.WEEK_BEFORE);
	  findDeathsDashboardData(TimePeriod.WEEK_CURRENT);
	  findDeathsDashboardData(TimePeriod.WEEK_LAST);
	  findDeathsDashboardData(TimePeriod.WEEK_BEFORE);
  }
  
  @Scheduled(fixedRate=5000)
  public void callMonthlySqlStatments(){  
	  findBirthsDashboardData(TimePeriod.MONTH_CURRENT);
	  findBirthsDashboardData(TimePeriod.MONTH_LAST);
	  findBirthsDashboardData(TimePeriod.MONTH_BEFORE);
	  findDeathsDashboardData(TimePeriod.MONTH_CURRENT);
	  findDeathsDashboardData(TimePeriod.MONTH_LAST);
	  findDeathsDashboardData(TimePeriod.MONTH_BEFORE);
  }
  
  @Override
  public void callQuartlySqlStatments() {
	  findBirthsDashboardData(TimePeriod.QUARTER_CURRENT);
	  findBirthsDashboardData(TimePeriod.QUARTER_LAST);
	  findBirthsDashboardData(TimePeriod.QUARTER_BEFORE);
	  findDeathsDashboardData(TimePeriod.QUARTER_CURRENT);
	  findDeathsDashboardData(TimePeriod.QUARTER_LAST);
	  findDeathsDashboardData(TimePeriod.QUARTER_BEFORE);
  }

  @Override
  public void callYearlySqlStatments() {
	  findBirthsDashboardData(TimePeriod.YEAR_CURRENT);
	  findBirthsDashboardData(TimePeriod.YEAR_LAST);
	  findBirthsDashboardData(TimePeriod.YEAR_BEFORE);
	  findDeathsDashboardData(TimePeriod.YEAR_CURRENT);
	  findDeathsDashboardData(TimePeriod.YEAR_LAST);
	  findDeathsDashboardData(TimePeriod.YEAR_BEFORE);
  }
  
  
  @Override
  public void findBirthsDashboardData(TimePeriod period) {
    
	List<Date> dates = findPeriodRange(period);
	
	deletedFile(fileLoactionBirth+period+".json");
	
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
    	mapper.writeValue(new File(fileLoactionBirth+period+".json"),dd);
    }catch (IOException e1) {
  	  logger.log(Level.SEVERE,"could not write too file" ,e1);
  	}
    
  }
  
  @Override
  public void findDeathsDashboardData(TimePeriod period) {
     
	List<Date> dates = findPeriodRange(period);
	
	deletedFile(fileLoactionDeath+period+".json");
	
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
        mapper.writeValue(new File(fileLoactionDeath+period+".json"),dd);
    } catch (IOException e1) {
    	  logger.log(Level.SEVERE,"could not write too file" ,e1);
    }
 

  }
   
  
  public void deletedFile(String fileName){
	File file = new File(fileName);
	if(file.exists()){
	  if(file.delete()){
		System.out.println("File: " + fileName + "deleted");
	  }
	  else{
		  System.out.println("File: " + fileName +" could not be deleted have you got it open?");
	  }
	}
  }
  
  
  public List<Date> findPeriodRange(TimePeriod period){
  
	List<Date> dates = new ArrayList<Date>();
	  
	switch (period){
	case WEEK_CURRENT :
	case WEEK_LAST:
	case WEEK_BEFORE:
		dates = findWeekRange(period);
		break;
	case MONTH_CURRENT :
	case MONTH_LAST:
	case MONTH_BEFORE:
		dates = findMonthRange(period);
		break;
	case QUARTER_CURRENT :
	case QUARTER_LAST:
	case QUARTER_BEFORE:
		dates = findQuarterRange(period);
		break;
	case YEAR_CURRENT :
	case YEAR_LAST:
	case YEAR_BEFORE:
		dates = findYearRange(period);
		break;
	}
	return dates;
  }
  
  @SuppressWarnings("static-access")
  public List<Date> findWeekRange(TimePeriod week){
	
	List<Date> dates = new ArrayList<Date>();
	  
	int dayDiff = 0;
	switch (week){
	case WEEK_CURRENT :
	  dayDiff = 0;
	  break;
	case WEEK_LAST:
	  dayDiff = 7;
	  break;
	case WEEK_BEFORE:
      dayDiff = 14;
	  break;
	default:
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



  @Override
  public List<Date> findMonthRange(TimePeriod month) {
	Calendar calendar = Calendar.getInstance();
    System.out.println("Month: " + calendar.get(Calendar.MONTH));
    return null;
  }
	
  @Override
  public List<Date> findQuarterRange(TimePeriod quarter) {
	// TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Date> findYearRange(TimePeriod year) {
    // TODO Auto-generated method stub
    return null;
  }
}
