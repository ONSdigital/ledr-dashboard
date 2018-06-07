package uk.gov.ons.lerp.poc.exception;

@SuppressWarnings("serial")
public class CannotFindDataException extends Exception {

  public CannotFindDataException(String message, Throwable ex) {
    super(message, ex);
  }
}
