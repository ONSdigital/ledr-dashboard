package uk.gov.ons.lerp.poc.exception;

@SuppressWarnings("serial")
public class CannotRetrieveDashboardData extends Exception {
  
  public CannotRetrieveDashboardData(String message, Throwable ex) {
    super(message, ex);
  } 
}
