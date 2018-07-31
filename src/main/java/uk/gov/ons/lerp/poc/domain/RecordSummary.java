package uk.gov.ons.lerp.poc.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
