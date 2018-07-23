package uk.gov.ons.lerp.poc.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.lerp.poc.domain.FileLocation;
import uk.gov.ons.lerp.poc.domain.RecordSummary;
import uk.gov.ons.lerp.poc.domain.TimePeriod;
import uk.gov.ons.lerp.poc.exception.CannotFindDataException;
import uk.gov.ons.lerp.poc.exception.CannotRetrieveDashboardData;
import uk.gov.ons.lerp.poc.repository.DataRepository;
import uk.gov.ons.lerp.poc.service.DashboardService;

@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService {

  @Autowired
  private DataRepository dataRepository;


  @Autowired
  private FileLocation fileLocation;

  private ObjectMapper mapper = new ObjectMapper();

  public RecordSummary retrieveBirthDashboardData(final String period) throws CannotRetrieveDashboardData {

    try {
      return mapper.readValue(new File(fileLocation.getFileLocationBirth() + period + ".json"), RecordSummary.class);
    } catch (IOException fileReaderError) {
      throw new CannotRetrieveDashboardData("cannot find file", fileReaderError);
    }

  }

  public RecordSummary retrieveDeathDashboardData(final String period) throws CannotRetrieveDashboardData {

    try {
      return mapper.readValue(new File(fileLocation.getFileLocationDeath() + period + ".json"), RecordSummary.class);
    } catch (IOException fileReaderError) {
      throw new CannotRetrieveDashboardData("cannot find file", fileReaderError);
    }

  }

  public RecordSummary retrieveDeathOccupationData(final String period) throws CannotRetrieveDashboardData {
    return null;
    //TODO: all logic for this endpoint.
  }


  @Scheduled(cron = "#{appConfig.querySchedule.weeklyCron}")
  private void callWeeklySqlStatements() {
    try {
      findBirthsDashboardData(TimePeriod.WEEK_CURRENT);
      findBirthsDashboardData(TimePeriod.WEEK_LAST);
      findBirthsDashboardData(TimePeriod.WEEK_BEFORE);
      findDeathsDashboardData(TimePeriod.WEEK_CURRENT);
      findDeathsDashboardData(TimePeriod.WEEK_LAST);
      findDeathsDashboardData(TimePeriod.WEEK_BEFORE);
    } catch (CannotFindDataException e1) {
      log.error(Level.SEVERE + "could not write to file", e1);
    }
  }

  @Scheduled(cron = "#{appConfig.querySchedule.monthlyCron}")
  private void callMonthlySqlStatements() {
    try {
      findBirthsDashboardData(TimePeriod.MONTH_CURRENT);
      findBirthsDashboardData(TimePeriod.MONTH_LAST);
      findBirthsDashboardData(TimePeriod.MONTH_BEFORE);
      findDeathsDashboardData(TimePeriod.MONTH_CURRENT);
      findDeathsDashboardData(TimePeriod.MONTH_LAST);
      findDeathsDashboardData(TimePeriod.MONTH_BEFORE);
    } catch (CannotFindDataException e1) {
      log.error(Level.SEVERE + "could not write to file", e1);
    }
  }

  @Scheduled(cron = "#{appConfig.querySchedule.quarterlyCron}")
  private void callQuartlySqlStatements() {
    try {
      findBirthsDashboardData(TimePeriod.QUARTER_CURRENT);
      findBirthsDashboardData(TimePeriod.QUARTER_LAST);
      findBirthsDashboardData(TimePeriod.QUARTER_BEFORE);
      findDeathsDashboardData(TimePeriod.QUARTER_CURRENT);
      findDeathsDashboardData(TimePeriod.QUARTER_LAST);
      findDeathsDashboardData(TimePeriod.QUARTER_BEFORE);
    } catch (CannotFindDataException e1) {
      log.error(Level.SEVERE + "could not write to file", e1);
    }
  }

  @Scheduled(cron = "#{appConfig.querySchedule.yearlyCron}")
  private void callYearlySqlStatements() {
    try {
      findBirthsDashboardData(TimePeriod.YEAR_CURRENT);
      findBirthsDashboardData(TimePeriod.YEAR_LAST);
      findBirthsDashboardData(TimePeriod.YEAR_BEFORE);
      findDeathsDashboardData(TimePeriod.YEAR_CURRENT);
      findDeathsDashboardData(TimePeriod.YEAR_LAST);
      findDeathsDashboardData(TimePeriod.YEAR_BEFORE);
    } catch (CannotFindDataException e1) {
      log.error(Level.SEVERE + "could not write to file", e1);
    }
  }

  private void findBirthsDashboardData(TimePeriod period) throws CannotFindDataException {

    List<Date> dates = findPeriodRange(period);

    deleteFile(fileLocation.getFileLocationBirth() + period + ".json");

    RecordSummary dd = new RecordSummary();
    System.out.println("*****************************************************************************************************************************");
    System.out.println("*****************************************************************************************************************************");
    System.out.println("*****************************************************************************************************************************");
    System.out.println("*****************************************************************************************************************************");
    System.out.println("Testing to see whether it's the dataRepository instance or the dates instance that is null:");
    System.out.println("The value of dataRepository is: " + dataRepository.toString());
    System.out.println("The value of dates is: " + dates.toString());
    System.out.println("*****************************************************************************************************************************");
    System.out.println("*****************************************************************************************************************************");
    System.out.println("*****************************************************************************************************************************");
    System.out.println("*****************************************************************************************************************************");
    dd.setRecordsReceived(dataRepository.findBirthsRecordsReceived(dates.get(0), dates.get(1)));
    dd.setFullyCoded(dataRepository.findBirthsFullyCoded(dates.get(0), dates.get(1)));
    dd.setOutstandingGeographyFull(dataRepository.findBirthsOutstandingGeographyFull(dates.get(0), dates.get(1)));
    dd.setOutstandingGeographyPOB(dataRepository.findBirthsOutstandingGeographyPOB(dates.get(0), dates.get(1)));
    dd.setOutstandingGeographyPOE(dataRepository.findBirthsOutstandingGeographyPOE(dates.get(0), dates.get(1)));
    dd.setOutstandingGeographyUR(dataRepository.findBirthsOutstandingGeographyUR(dates.get(0), dates.get(1)));
    dd.setOutstandingOccupation(dataRepository.findBirthsOutstandingOccupation(dates.get(0), dates.get(1)));
    dd.setOutstandingCause(dataRepository.findBirthsOutstandingCause(dates.get(0), dates.get(1)));
    try {
      mapper.writeValue(new File(fileLocation.getFileLocationBirth() + period + ".json"), dd);
    } catch (IOException e) {
      throw new CannotFindDataException("error mappering data", e);
    }
  }

  private void findDeathsDashboardData(TimePeriod period) throws CannotFindDataException {

    List<Date> dates = findPeriodRange(period);

    deleteFile(fileLocation.getFileLocationDeath() + period + ".json");

    RecordSummary dd = new RecordSummary();
    dd.setRecordsReceived(dataRepository.findDeathsRecordsReceived(dates.get(0), dates.get(1)));
    dd.setFullyCoded(dataRepository.findDeathsFullyCoded(dates.get(0), dates.get(1)));
    dd.setOutstandingGeographyFull(dataRepository.findDeathsOutstandingGeographyFull(dates.get(0), dates.get(1)));
    dd.setOutstandingGeographyPOB(dataRepository.findDeathsOutstandingGeographyPOB(dates.get(0), dates.get(1)));
    dd.setOutstandingGeographyPOE(dataRepository.findDeathsOutstandingGeographyPOE(dates.get(0), dates.get(1)));
    dd.setOutstandingGeographyUR(dataRepository.findDeathsOutstandingGeographyUR(dates.get(0), dates.get(1)));
    dd.setOutstandingOccupation(dataRepository.findDeathsOutstandingOccupation(dates.get(0), dates.get(1)));
    dd.setOutstandingCause(dataRepository.findDeathsOutstandingCause(dates.get(0), dates.get(1)));

    try {
      mapper.writeValue(new File(fileLocation.getFileLocationDeath() + period + ".json"), dd);
    } catch (IOException e) {
      throw new CannotFindDataException("error mappering data", e);
    }
  }

  private void deleteFile(String fileName) {
    File file = new File(fileName);
    if (file.exists()) {
      if (file.delete()) {
        log.debug("File: " + fileName + "deleted");
      } else {
        log.error("File: " + fileName + " could not be deleted have you got it open?");
      }
    }
  }

  private List<Date> findPeriodRange(TimePeriod period) {

    List<Date> dates = new ArrayList<Date>();

    switch (period) {
      case WEEK_CURRENT:
      case WEEK_LAST:
      case WEEK_BEFORE:
        dates = findWeekRange(period);
        break;
      case MONTH_CURRENT:
      case MONTH_LAST:
      case MONTH_BEFORE:
        dates = findMonthRange(period);
        break;
      case QUARTER_CURRENT:
      case QUARTER_LAST:
      case QUARTER_BEFORE:
        dates = findQuarterRange(period);
        break;
      case YEAR_CURRENT:
      case YEAR_LAST:
      case YEAR_BEFORE:
        dates = findYearRange(period);
        break;
    }
    return dates;
  }

  private List<Date> findWeekRange(TimePeriod week) {

    List<Date> dates = new ArrayList<Date>();

    int dayDiff = 0;
    switch (week) {
      case WEEK_CURRENT:
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
    dateCurrentSat.add(Calendar.DAY_OF_WEEK, -(dateCurrentSat.get(Calendar.DAY_OF_WEEK) + dayDiff));

    Calendar dateCurrentFri = Calendar.getInstance();
    dateCurrentFri.add(Calendar.DAY_OF_WEEK, -(dateCurrentFri.get(Calendar.DAY_OF_WEEK) - 6 + dayDiff));

    SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-YY");
    Date dateSat = dateCurrentSat.getTime();
    dateFormat.format(dateSat);

    Date dateFri = dateCurrentFri.getTime();
    dateFormat.format(dateFri);

    dates.add(dateSat);
    dates.add(dateFri);
    return dates;
  }


  private List<Date> findMonthRange(TimePeriod month) {
    Calendar calendar = Calendar.getInstance();
    System.out.println("Month: " + calendar.get(Calendar.MONTH));
    return null;
  }

  private List<Date> findQuarterRange(TimePeriod quarter) {
    // TODO Auto-generated method stub
    return null;
  }

  private List<Date> findYearRange(TimePeriod year) {
    // TODO Auto-generated method stub
    return null;
  }
}
