package uk.gov.ons.lerp.poc.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class OccupationExtra {

  private Integer NoninquestRecieved;
  
  private Integer OutStandingNonNeonates;
  
  private Integer OutStandingNeonates;
  
  private Integer ErrorsAndWarningsNonNeonates;
  
  private Integer ErrorsAndWarningsNeonates;
  
  private Integer Inquest;
  
  private Integer InquestOutstandingNonNeonates;
  
  private Integer InquestOutstandingNeonates;
  
  private Integer InquestErrorsAndWarningsNonNeonates;
  
  private Integer InquestErrorsAndWarningsNeonates;
  
  private Integer OutstandingYellow;
  
  private Integer OutstandingBlue;

}
