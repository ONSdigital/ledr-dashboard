package uk.gov.ons.lerp.poc.service;

import java.util.Date;
import java.util.List;

public interface DashboardService {

  void callSqlStatments();
  void findBirthsDashboardData(String week);
  void findDeathsDashboardData(String week);
  List<Date> findWeekRange(String week);
	 
}
