package uk.gov.ons.lerp.poc.representation;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DashboardData {
	
	  private Integer recordsReceived;

	  private Integer fullyCoded;

	  private Integer outstandingGeographyFull;
	  
	  private Integer outstandingGeographyPOB;
	  
	  private Integer outstandingGeographyPOE;
	  
	  private Integer outstandingGeographyUR;
	  
	  private Integer outstandingOccupation;

	  private Integer outstandingCause;
	  
	  public String toString(){
		  String info = String.format("Records Rececieved = %d, Fully Coded = %d, Outstanding Geography Full = %d, Outstanding Geography POB = %d,"
		  	+ " Outstanding Geography POE = %d, Outstanding Geography UR = %d, Outstanding Occupation = %d, Outstanding Cause= %d", 
		    recordsReceived,fullyCoded,outstandingGeographyFull,outstandingGeographyPOB,outstandingGeographyPOE,outstandingGeographyUR,outstandingOccupation,outstandingCause);
		  return info;
	  }
}
