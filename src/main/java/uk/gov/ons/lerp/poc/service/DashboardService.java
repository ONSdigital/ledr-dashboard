package uk.gov.ons.lerp.poc.service;

import uk.gov.ons.lerp.poc.domain.RecordSummary;
import uk.gov.ons.lerp.poc.exception.CannotRetrieveDashboardData;

/**
 *  Service responsible for dealing with Management Information requests
 *
 */
public interface DashboardService {
  
  /**
   * Retrieve Birth summary data for specified period.
   * @param period
   * @return RecordSummary for birth data
   * @throws CannotRetrieveDashboardData
   */
  RecordSummary retrieveBirthSummaryData(final String period) throws CannotRetrieveDashboardData;

  /**
   * Retrieve Death summary data for specified period.
   * @param period
   * @return RecordSummary for death data
   * @throws CannotRetrieveDashboardData
   */
  RecordSummary retrieveDeathSummaryData(final String period) throws CannotRetrieveDashboardData;

  /**
   * Retrieve Death Cause Coding detail data for specified period.
   * @param period
   * @return CauseDetail for Death data
   * @throws CannotRetrieveDashboardData
   */
  RecordSummary retrieveDeathCauseDetailData(final String period) throws CannotRetrieveDashboardData;
	 
}
