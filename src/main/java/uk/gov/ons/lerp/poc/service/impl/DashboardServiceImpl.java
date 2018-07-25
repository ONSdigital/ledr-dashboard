package uk.gov.ons.lerp.poc.service.impl;

import static java.time.temporal.TemporalAdjusters.previous;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.lerp.poc.config.AppConfig;
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
  private AppConfig appConfig;

  private ObjectMapper mapper = new ObjectMapper();

  public RecordSummary retrieveBirthDashboardData(final String period) throws CannotRetrieveDashboardData {

    try {
      return mapper.readValue(new File(appConfig.getFileLocation().getBirth() + period + ".json"), RecordSummary.class);
    } catch (IOException fileReaderError) {
      throw new CannotRetrieveDashboardData("cannot find file", fileReaderError);
    }

  }

  public RecordSummary retrieveDeathDashboardData(final String period) throws CannotRetrieveDashboardData {

    try {
      return mapper.readValue(new File(appConfig.getFileLocation().getBirth() + period + ".json"), RecordSummary.class);
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

    deleteFile(appConfig.getFileLocation().getBirth() + period + ".json");

    RecordSummary dd = new RecordSummary();

    dd.setRecordsReceived(dataRepository.findBirthsRecordsReceived(dates.get(0), dates.get(1)));
    dd.setFullyCoded(dataRepository.findBirthsFullyCoded(dates.get(0), dates.get(1)));
    dd.setOutstandingGeographyFull(dataRepository.findBirthsOutstandingGeographyFull(dates.get(0), dates.get(1)));
    dd.setOutstandingGeographyPOB(dataRepository.findBirthsOutstandingGeographyPOB(dates.get(0), dates.get(1)));
    dd.setOutstandingGeographyPOE(dataRepository.findBirthsOutstandingGeographyPOE(dates.get(0), dates.get(1)));
    dd.setOutstandingGeographyUR(dataRepository.findBirthsOutstandingGeographyUR(dates.get(0), dates.get(1)));
    dd.setOutstandingOccupation(dataRepository.findBirthsOutstandingOccupation(dates.get(0), dates.get(1)));
    dd.setOutstandingCause(dataRepository.findBirthsOutstandingCause(dates.get(0), dates.get(1)));
    try {
      mapper.writeValue(new File(appConfig.getFileLocation().getBirth() + period + ".json"), dd);
    } catch (IOException e) {
      throw new CannotFindDataException("error mappering data", e);
    }
  }

  private void findDeathsDashboardData(TimePeriod period) throws CannotFindDataException {

    List<Date> dates = findPeriodRange(period);

    deleteFile(appConfig.getFileLocation().getBirth() + period + ".json");

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
      mapper.writeValue(new File(appConfig.getFileLocation().getBirth() + period + ".json"), dd);
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
    LocalDate localDate = LocalDate.now();

    LocalDate satOfWeek = localDate.with(previous(DayOfWeek.SATURDAY));
    LocalDate friOfWeek = localDate.with(DayOfWeek.FRIDAY);

    switch (week) {
      case WEEK_CURRENT:
        break;
      case WEEK_LAST:
    	satOfWeek = satOfWeek.minusDays(7);
    	friOfWeek = friOfWeek.minusDays(7);
        break;
      case WEEK_BEFORE:
    	satOfWeek = satOfWeek.minusDays(14);
    	friOfWeek = friOfWeek.minusDays(14);
      }

    dates.add(java.sql.Date.valueOf(satOfWeek));
    dates.add(java.sql.Date.valueOf(friOfWeek));
    return dates;
  }

  private List<Date> findMonthRange(TimePeriod month) {

    List<Date> dates = new ArrayList<Date>();
    LocalDate localDate = LocalDate.now();

    LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
    LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());

    switch (month) {
      case MONTH_CURRENT:
        break;
      case MONTH_LAST:
    	firstDayOfMonth = firstDayOfMonth.minusMonths(1);
    	lastDayOfMonth = lastDayOfMonth.minusMonths(1);
        break;
      case MONTH_BEFORE:
    	firstDayOfMonth = firstDayOfMonth.minusMonths(2);
    	lastDayOfMonth = lastDayOfMonth.minusMonths(2);
     }

    dates.add(java.sql.Date.valueOf(firstDayOfMonth));
    dates.add(java.sql.Date.valueOf(lastDayOfMonth));
    return dates;

  }

  private List<Date> findQuarterRange(TimePeriod quarter) {

		List<Date> dates = new ArrayList<Date>();
		LocalDate localDate = LocalDate.now();
		
		LocalDate firstDayOfQuarter = localDate.with(localDate.getMonth().firstMonthOfQuarter())
				.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastDayOfQuarter = firstDayOfQuarter.plusMonths(2).with(TemporalAdjusters.lastDayOfMonth());

	    switch (quarter) {
	      case QUARTER_CURRENT:
	        break;
	      case QUARTER_LAST:
	    	firstDayOfQuarter = firstDayOfQuarter.minusMonths(3);
	    	lastDayOfQuarter = lastDayOfQuarter.minusMonths(3);
	        break;
	      case QUARTER_BEFORE:
	    	firstDayOfQuarter = firstDayOfQuarter.minusMonths(6);
	    	lastDayOfQuarter = lastDayOfQuarter.minusMonths(6);
	    }

	    dates.add(java.sql.Date.valueOf(firstDayOfQuarter));
	    dates.add(java.sql.Date.valueOf(lastDayOfQuarter));
	    return dates;
  }

  private List<Date> findYearRange(TimePeriod year) {
		List<Date> dates = new ArrayList<Date>();
		LocalDate localDate = LocalDate.now();
		
		LocalDate firstDayOfYear = localDate.with(TemporalAdjusters.firstDayOfYear());
		LocalDate lastDayOfYear = localDate.with(TemporalAdjusters.lastDayOfYear());

	    switch (year) {
	      case YEAR_CURRENT:
	        break;
	      case YEAR_LAST:
	    	firstDayOfYear = firstDayOfYear.minusYears(1);
	    	lastDayOfYear = lastDayOfYear.minusYears(1);
	        break;
	      case YEAR_BEFORE:
	    	firstDayOfYear = firstDayOfYear.minusYears(2);
	    	lastDayOfYear = lastDayOfYear.minusYears(2);
	    }

	    dates.add(java.sql.Date.valueOf(firstDayOfYear));
	    dates.add(java.sql.Date.valueOf(lastDayOfYear));
	    return dates;
  }
}
