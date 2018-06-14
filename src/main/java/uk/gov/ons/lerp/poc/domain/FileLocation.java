package uk.gov.ons.lerp.poc.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileLocation {

  private String fileLocationBirth;
 
  private String fileLocationDeath;
  
}
