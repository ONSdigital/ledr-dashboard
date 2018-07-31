package uk.gov.ons.lerp.poc.service;

/**
 * Service to create summary management information
 *
 */
public interface DataSummaryService {

  /**
   * Create weekly summary management information.
   */
  void createWeeklySummary();

  /**
   * Create monthly summary management information.
   */
  void createMonthlySummary();

  /**
   * Create quarterly summary management information
   */
  void createQuarterlySummary();

  /**
   * Create annual summary management information
   */
  void createAnnualSummary();  
}
