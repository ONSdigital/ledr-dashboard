package uk.gov.ons.lerp.poc.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RecordSummary {
	
	  private Integer recordsReceived;

	  private Integer fullyCoded;

	  private Integer outstandingGeographyFull;
	  
	  private Integer outstandingGeographyPOB;
	  
	  private Integer outstandingGeographyPOE;
	  
	  private Integer outstandingGeographyUR;
	  
	  private Integer outstandingOccupation;

	  private Integer outstandingCause;
	  
}
