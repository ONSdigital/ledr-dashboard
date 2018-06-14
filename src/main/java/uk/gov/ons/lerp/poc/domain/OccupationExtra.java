package uk.gov.ons.lerp.poc.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class OccupationExtra {

  private Integer NonInquestReceived;
  
  private Integer NonInquestReceivedOutstandingNonNeonates;
  
  private Integer NonInquestReceivedOutstandingNeonates;
  
  private Integer NonInquestReceivedErrorsAndWarningsNonNeonates;
  
  private Integer NonInquestReceivedErrorsAndWarningsNeonates;
  
  private Integer InquestPartV;
  
  private Integer InquestPartVOutstandingNonNeonates;
  
  private Integer InquestPartVOutstandingNeonates;
  
  private Integer InquestPartVErrorsAndWarningsNonNeonates;
  
  private Integer InquestPartVErrorsAndWarningsNeonates;

  private Integer InquestAdjourned;

  private Integer InquestAdjournedOutstandingYellow;
  
  private Integer InquestAdjournedOutstandingBlue;

}
