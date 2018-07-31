package uk.gov.ons.lerp.poc.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.lerp.poc.config.AppConfig;
import uk.gov.ons.lerp.poc.domain.RecordSummary;
import uk.gov.ons.lerp.poc.exception.CannotRetrieveDashboardData;
import uk.gov.ons.lerp.poc.repository.DataRepository;
import uk.gov.ons.lerp.poc.service.DashboardService;

@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService {

  @Autowired
  private DataRepository dataRepository;

  @Autowired
  private AppConfig appConfig;

  @Autowired
  private ObjectMapper jsonMapper;

  public RecordSummary retrieveBirthSummaryData(final String period) throws CannotRetrieveDashboardData {

    try {
      return jsonMapper.readValue(new File(appConfig.getFileLocation().getBirth() + period + ".json"), RecordSummary.class);
    } catch (IOException fileReaderError) {
      throw new CannotRetrieveDashboardData("cannot find file", fileReaderError);
    }
  }

  public RecordSummary retrieveDeathSummaryData(final String period) throws CannotRetrieveDashboardData {

    try {
      return jsonMapper.readValue(new File(appConfig.getFileLocation().getDeath() + period + ".json"), RecordSummary.class);
    } catch (IOException fileReaderError) {
      throw new CannotRetrieveDashboardData("cannot find file", fileReaderError);
    }
  }

  public RecordSummary retrieveDeathCauseDetailData(final String period) throws CannotRetrieveDashboardData {
    return null;
    //TODO: all logic for this endpoint.
  }
}
