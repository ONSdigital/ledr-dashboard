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
import uk.gov.ons.lerp.poc.domain.CauseDetail;
import uk.gov.ons.lerp.poc.domain.RecordSummary;
import uk.gov.ons.lerp.poc.domain.TimePeriod;
import uk.gov.ons.lerp.poc.exception.CannotFindDataException;
import uk.gov.ons.lerp.poc.repository.DataRepository;
import uk.gov.ons.lerp.poc.service.DataSummaryService;

@Slf4j
@Service
public class DataSummaryServiceImpl implements DataSummaryService {

  @Autowired
  private DataRepository dataRepository;

  @Autowired
  private AppConfig appConfig;

  @Autowired
  private ObjectMapper jsonMapper;
	  
  @Scheduled(cron = "#{appConfig.querySchedule.weeklyCron}")
  @Override
  public void createWeeklySummary() {
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
  @Override
  public void createMonthlySummary() {
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
  @Override
  public void createQuarterlySummary() {
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
  @Override
  public void createAnnualSummary() {
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

    RecordSummary rs = RecordSummary.builder()
      .recordsReceived(dataRepository.findBirthsRecordsReceived(dates.get(0), dates.get(1)))
      .fullyCoded(dataRepository.findBirthsFullyCoded(dates.get(0), dates.get(1)))
      .outstandingGeographyFull(dataRepository.findBirthsOutstandingGeographyFull(dates.get(0), dates.get(1)))
      .outstandingGeographyPOB(dataRepository.findBirthsOutstandingGeographyPOB(dates.get(0), dates.get(1)))
      .outstandingGeographyPOE(dataRepository.findBirthsOutstandingGeographyPOE(dates.get(0), dates.get(1)))
      .outstandingGeographyUR(dataRepository.findBirthsOutstandingGeographyUR(dates.get(0), dates.get(1)))
      .outstandingOccupation(dataRepository.findBirthsOutstandingOccupation(dates.get(0), dates.get(1)))
      .outstandingCause(dataRepository.findBirthsOutstandingCause(dates.get(0), dates.get(1)))
      .build();

    try {
    	jsonMapper.writeValue(new File(appConfig.getFileLocation().getBirth() + period + ".json"), rs);
    } catch (IOException e) {
      throw new CannotFindDataException("error mappering data", e);
    }
  }

  private void findDeathsDashboardData(TimePeriod period) throws CannotFindDataException {

    List<Date> dates = findPeriodRange(period);

    deleteFile(appConfig.getFileLocation().getDeath() + period + ".json");
    deleteFile(appConfig.getFileLocation().getDeath() + "causedetail"+ period + ".json");
     
    RecordSummary rs = RecordSummary.builder()
      .recordsReceived(dataRepository.findDeathsRecordsReceived(dates.get(0), dates.get(1)))
      .fullyCoded(dataRepository.findDeathsFullyCoded(dates.get(0), dates.get(1)))
      .outstandingGeographyFull(dataRepository.findDeathsOutstandingGeographyFull(dates.get(0), dates.get(1)))
      .outstandingGeographyPOB(dataRepository.findDeathsOutstandingGeographyPOB(dates.get(0), dates.get(1)))
      .outstandingGeographyPOE(dataRepository.findDeathsOutstandingGeographyPOE(dates.get(0), dates.get(1)))
      .outstandingGeographyUR(dataRepository.findDeathsOutstandingGeographyUR(dates.get(0), dates.get(1)))
      .outstandingOccupation(dataRepository.findDeathsOutstandingOccupation(dates.get(0), dates.get(1)))
      .outstandingCause(dataRepository.findDeathsOutstandingCause(dates.get(0), dates.get(1)))
      .build();

    CauseDetail cd = CauseDetail.builder()
      .nonInquestReceived(dataRepository.findDeathsNonInquestRecieved(dates.get(0), dates.get(1)))
      .nonInquestOutstandingNonNeonates(dataRepository.findDeathsNonInquestOutstandingNonNeonates(dates.get(0), dates.get(1)))
      .nonInquestOutstandingNeonates(dataRepository.findDeathsNonInquestOutstandingNeonates(dates.get(0), dates.get(1)))
      .nonInquestErrorsAndWarningsNonNeonates(dataRepository.findDeathsNonInquestErrorsAndWarningsNonNeonates(dates.get(0), dates.get(1)))
      .nonInquestErrorsAndWarningsNeonates(dataRepository.findDeathsNonInquestErrorsAndWarningsNeonates(dates.get(0), dates.get(1)))
      .inquestReceived(dataRepository.findDeathsInquestReceived(dates.get(0), dates.get(1)))
      .inquestOutstandingNonNeonates(dataRepository.findDeathsInquestOutstandingNonNeonates(dates.get(0), dates.get(1)))
      .inquestOutstandingNeonates(dataRepository.findDeathsInquestOutstandingNeonates(dates.get(0), dates.get(1)))
      .inquestErrorsAndWarningsNonNeonates(dataRepository.findDeathsInquestErrorsAndWarningsNonNeonates(dates.get(0), dates.get(1)))
      .inquestErrorsAndWarningsNeonates(dataRepository.findDeathsInquestErrorsAndWarningsNeonates(dates.get(0), dates.get(1)))
      .inquestAdjournedOutstandingYellow(dataRepository.findDeathsInquestAdjournedYellow(dates.get(0), dates.get(1)))
      .inquestAdjournedOutstandingBlue(dataRepository.findDeathsInquestAdjounedBlue(dates.get(0), dates.get(1)))
      .build();

    try {
    	jsonMapper.writeValue(new File(appConfig.getFileLocation().getDeath() + period + ".json"), rs);
    	jsonMapper.writeValue(new File(appConfig.getFileLocation().getDeath() + "causedetail" + period + ".json"), cd);
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
