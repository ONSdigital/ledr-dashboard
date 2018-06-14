package uk.gov.ons.lerp.poc.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class OccupationExtra {

  private Integer nonInquestReceived;
  
  private Integer nonInquestReceivedOutstandingNonNeonates;
  
  private Integer nonInquestReceivedOutstandingNeonates;
  
  private Integer nonInquestReceivedErrorsAndWarningsNonNeonates;
  
  private Integer nonInquestReceivedErrorsAndWarningsNeonates;
  
  private Integer inquestPartV;
  
  private Integer inquestPartVOutstandingNonNeonates;
  
  private Integer inquestPartVOutstandingNeonates;
  
  private Integer inquestPartVErrorsAndWarningsNonNeonates;
  
  private Integer inquestPartVErrorsAndWarningsNeonates;

  private Integer inquestAdjourned;

  private Integer inquestAdjournedOutstandingYellow;
  
  private Integer inquestAdjournedOutstandingBlue;

}
