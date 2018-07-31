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
public class CauseDetail {

  private Integer nonInquestReceived;
  
  private Integer nonInquestOutstandingNonNeonates;
  
  private Integer nonInquestOutstandingNeonates;
  
  private Integer nonInquestErrorsAndWarningsNonNeonates;
  
  private Integer nonInquestErrorsAndWarningsNeonates;
  
  private Integer inquestReceived;
  
  private Integer inquestOutstandingNonNeonates;
  
  private Integer inquestOutstandingNeonates;
  
  private Integer inquestErrorsAndWarningsNonNeonates;
  
  private Integer inquestErrorsAndWarningsNeonates;

  private Integer inquestAdjournedOutstandingYellow;
  
  private Integer inquestAdjournedOutstandingBlue;

}
