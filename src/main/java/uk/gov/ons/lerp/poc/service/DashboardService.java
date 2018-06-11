package uk.gov.ons.lerp.poc.service;

import uk.gov.ons.lerp.poc.domain.RecordSummary;
import uk.gov.ons.lerp.poc.exception.CannotRetrieveDashboardData;

public interface DashboardService {

  RecordSummary retrieveBirthDashboardData(final String period) throws CannotRetrieveDashboardData;
  RecordSummary retrieveDeathDashboardData(final String period) throws CannotRetrieveDashboardData;
  RecordSummary retrieveDeathOccupationData(final String period) throws CannotRetrieveDashboardData;
	 
}
