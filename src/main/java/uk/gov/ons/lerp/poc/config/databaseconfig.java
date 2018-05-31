package uk.gov.ons.lerp.poc.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;


public class databaseconfig {
  
  @Bean
  public DataSource primaryDataSource() {
    JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
    DataSource dataSource = dataSourceLookup.getDataSource("jdbc/ledrDS");
    return dataSource;
  }
  
}
