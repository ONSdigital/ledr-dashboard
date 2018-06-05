package uk.gov.ons.lerp.poc.service;

import java.util.Date;
import java.util.List;

import uk.gov.ons.lerp.poc.representation.DashboardData;
import uk.gov.ons.lerp.poc.representation.TimePeriod;

public interface DashboardService {

	
  DashboardData retrieveDashboardData(final String topic, final String period);
  void callWeeklySqlStatments();
  void callMonthlySqlStatments();
  void callQuartlySqlStatments();
  void callYearlySqlStatments();
  void findBirthsDashboardData(TimePeriod period);
  void findDeathsDashboardData(TimePeriod period);
  List<Date> findPeriodRange(TimePeriod period);
  List<Date> findWeekRange(TimePeriod week);
  List<Date> findMonthRange(TimePeriod month);
  List<Date> findQuarterRange(TimePeriod quarter);
  List<Date> findYearRange(TimePeriod year);
	 
}
