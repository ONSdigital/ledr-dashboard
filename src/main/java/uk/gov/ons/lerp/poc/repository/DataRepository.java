package uk.gov.ons.lerp.poc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uk.gov.ons.lerp.poc.exception.CannotFindDataException;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DataRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");

  public int findBirthsRecordsReceived(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);
    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BIRTH_REG BR, BIRTH_VAR BV "
            + "WHERE BR.REG_TYPE= 1 "
            + "AND BR.BTC_BIRTH_EVENT_ID = BV.BTC_BIRTH_EVENT_ID "
            + "AND BR.REG_TYPE= 1 "
            + "AND BR.REG_CANCELLED_IND = 'N' "
            + "AND BV.REG_EXCL_OUTPUT is NULL "
            + "AND BV.LATEST= 1 "
            + "AND BR.LATEST= 1 "
            + "AND BR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (RuntimeException ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findBirthsFullyCoded(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BIRTH_REG BR, BIRTH_VAR BV "
            + "WHERE BR.REG_TYPE= 1 "
            + "AND BR.BTC_BIRTH_EVENT_ID = BV.BTC_BIRTH_EVENT_ID "
            + "AND BR.REG_TYPE= 1 "
            + "AND BR.REG_CANCELLED_IND = 'N' "
            + "AND BV.REG_EXCL_OUTPUT is NULL "
            + "AND BV.LATEST= 1 "
            + "AND BR.LATEST= 1 "
            + "AND BV.QI_FULLY_CODED = 'Y' "
            + "AND BR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }


  public int findBirthsOutstandingGeographyFull(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BIRTH_REG BR, BIRTH_VAR BV "
            + "WHERE BR.REG_TYPE= 1 "
            + "AND BR.BTC_BIRTH_EVENT_ID = BV.BTC_BIRTH_EVENT_ID "
            + "AND BR.REG_TYPE= 1 "
            + "AND BR.REG_CANCELLED_IND = 'N' "
            + "AND BV.REG_EXCL_OUTPUT is NULL "
            + "AND BV.LATEST= 1 "
            + "AND BR.LATEST= 1 "
            + "AND (BV.QI_GEOG_POB = 'N' "
            + "OR BV.QI_GEOG_POE = 'N' "
            + "OR BV.QI_GEOG_UR = 'N') "
            + "AND BR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findBirthsOutstandingGeographyPOB(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BIRTH_REG BR, BIRTH_VAR BV "
            + "WHERE BR.REG_TYPE= 1 "
            + "AND BR.BTC_BIRTH_EVENT_ID = BV.BTC_BIRTH_EVENT_ID "
            + "AND BR.REG_TYPE= 1 "
            + "AND BR.REG_CANCELLED_IND = 'N' "
            + "AND BV.REG_EXCL_OUTPUT is NULL "
            + "AND BV.LATEST= 1 "
            + "AND BR.LATEST= 1 "
            + "AND BV.QI_GEOG_POB = 'N' "
            + "AND BR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findBirthsOutstandingGeographyPOE(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BIRTH_REG BR, BIRTH_VAR BV "
            + "WHERE BR.REG_TYPE= 1 "
            + "AND BR.BTC_BIRTH_EVENT_ID = BV.BTC_BIRTH_EVENT_ID "
            + "AND BR.REG_TYPE= 1 "
            + "AND BR.REG_CANCELLED_IND = 'N' "
            + "AND BV.REG_EXCL_OUTPUT is NULL "
            + "AND BV.LATEST= 1 "
            + "AND BR.LATEST= 1 "
            + "AND BV.QI_GEOG_POE = 'N' "
            + "AND BR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findBirthsOutstandingGeographyUR(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BIRTH_REG BR, BIRTH_VAR BV "
            + "WHERE BR.REG_TYPE= 1 "
            + "AND BR.BTC_BIRTH_EVENT_ID = BV.BTC_BIRTH_EVENT_ID "
            + "AND BR.REG_TYPE= 1 "
            + "AND BR.REG_CANCELLED_IND = 'N' "
            + "AND BV.REG_EXCL_OUTPUT is NULL "
            + "AND BV.LATEST= 1 "
            + "AND BR.LATEST= 1 "
            + "AND BV.QI_GEOG_UR = 'N' "
            + "AND BR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }


  public int findBirthsOutstandingOccupation(Date sat, Date fri) throws CannotFindDataException {
    //TODO: This statement below needs looking into all the required fields are in it,
    //      the count for this statement is for only the number of birth records not the number of errors for occupation
    //      since there can be two error in occupation for a single record e.g. the mother and farther both have occupation errors
    //      this count only cares if one or both of them have errors and if so then the count is increased by one.
    //      Narinder has been a great help in doing helping to solve this, if you ever get stuck go to her for further help.


    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BIRTH_REG BR, BIRTH_VAR BV, PL_OCCUPATION_CODING OC,  VAILDATION_ERRORS VE"
            + "WHERE BR.REG_TYPE= 1 "
            + "AND BR.BTC_BIRTH_EVENT_ID = BV.BTC_BIRTH_EVENT_ID "
            + "AND BR.REG_TYPE= 1 "
            + "AND BR.REG_CANCELLED_IND = 'N' "
            + "AND BV.REG_EXCL_OUTPUT is NULL "
            + "AND BV.LATEST= 1 "
            + "AND BR.LATEST= 1 "
            + "AND BV.QI_OCC = 'N' "
            + "AND BR.BTC_BIRTH_EVENT_ID = OC.TET_EVENT_ID "
            + "AND ((OC.PSU_STATUS_ID = 1 "
            + "AND BR.BTC_BIRTH_EVENT_ID = VE.TET_EVENT_ID "
            + "AND VE.SAY_SA = 'BIRTHS' "
            + "AND VE.VCG_CIM_TYPE_ITEM_ID = 656) "
            + "OR  OC.PSU_STATUS_ID != 1) "
            + "AND BR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findBirthsOutstandingCause(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BIRTH_REG BR, BIRTH_VAR BV "
            + "WHERE BR.REG_TYPE= 1 "
            + "AND BR.BTC_BIRTH_EVENT_ID = BV.BTC_BIRTH_EVENT_ID "
            + "AND BR.REG_TYPE= 1 "
            + "AND BR.REG_CANCELLED_IND = 'N' "
            + "AND BV.REG_EXCL_OUTPUT is NULL "
            + "AND BV.LATEST= 1 "
            + "AND BR.LATEST= 1 "
            + "AND BV.QI_CAUSE = 'N' "
            + "AND BR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  //Deaths
  public int findDeathsRecordsReceived(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsFullyCoded(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.QI_FULLY_CODED = 'Y' "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsOutstandingGeographyFull(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND (DV.QI_GEOG_POB = 'N' "
            + "OR DV.QI_GEOG_POE = 'N' "
            + "OR DV.QI_GEOG_UR = 'N') "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsOutstandingGeographyPOB(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.QI_GEOG_POB = 'N' "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsOutstandingGeographyPOE(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.QI_GEOG_POE = 'N' "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsOutstandingGeographyUR(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.QI_GEOG_UR = 'N' "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }


  public int findDeathsOutstandingOccupation(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.QI_OCC = 'N' "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsOutstandingCause(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.QI_CAUSE = 'N' "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsNonInquestRecieved(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.COR_INQ_CERTFIFCATE_TYPE = 0 "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsOutstandingNonNeonates(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.COR_INQ_CERTFIFCATE_TYPE = 0 "
            + "AND DV.COD_PV = 0 "
            + "AND DV.QI_CAUSE = 'N' "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsOutstandingNeonates(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.COR_INQ_CERTIFICATE_TYPE = 0 "
            + "AND DV.COD_PV = 1 "
            + "AND DV.QI_CAUSE = 'N' "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsErrorsAndWarningsNonNeonates(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV, VAILDATION_ERRORS VE "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.COR_INQ_CERTIFICATE_TYPE = 0 "
            + "AND DV.COD_PV = 0 "
            + "AND DV.QI_CAUSE = 'N' "
            + "AND DR.DTC_DEATH_EVENT_ID = VE.TET_EVENT_ID "
            + "AND VE.SAY_SA = 'DEATHS' "
            + "AND (VE.VCG_CIM_TYPE_ITEM_ID = 898 OR VE.VCG_CIM_TYPE_ITEM_ID = 2113)"
            //TODO: add OVERRIDE_USER is blank
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsErrorsAndWarningsNeonates(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV, VAILDATION_ERRORS VE "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.COR_INQ_CERTIFICATE_TYPE = 0 "
            + "AND DV.COD_PV = 1 "
            + "AND DV.QI_CAUSE = 'N' "
            + "AND DR.DTC_DEATH_EVENT_ID = VE.TET_EVENT_ID "
            + "AND VE.SAY_SA = 'DEATHS' "
            + "AND (VE.VCG_CIM_TYPE_ITEM_ID = 898 OR VE.VCG_CIM_TYPE_ITEM_ID = 2113)"
            //TODO: add OVERRIDE_USER is blank
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsInquest(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV, VAILDATION_ERRORS VE "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.COR_INQ_CERTIFICATE_TYPE = 2 "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsInquestOutstandingNonNeonates(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV, VALIDATION_ERRORS VE "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.COR_INQ_CERTIFICATE_TYPE = 2 "
            + "AND DV.COD_PV = 0 "
            + "AND DV.QI_CAUSE = 'N' "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsInquestOutstandingNeonates(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV, VALIDATION_ERRORS VE "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.COR_INQ_CERTIFICATE_TYPE = 2 "
            + "AND DV.COD_PV = 1 "
            + "AND DV.QI_CAUSE = 'N' "
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsInquestErrorsAndWarningsNonNeonates(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV, VALIDATION_ERRORS VE "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.COR_INQ_CERTIFICATE_TYPE = 2 "
            + "AND DV.COD_PV = 0 "
            + "AND DV.QI_CAUSE = 'N' "
            + "AND DR.DTC_DEATH_EVENT_ID = VE.TET_EVENT_ID "
            + "AND VE.SAY_SA = 'DEATHS' "
            + "AND (VE.VCG_CIM_TYPE_ITEM_ID = 898 OR VE.VCG_CIM_TYPE_ITEM_ID = 2113)"
            //TODO: add OVERRIDE_USER is blank
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsInquestErrorsAndWarningsNeonates(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV, VALIDATION_ERRORS VE "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.COR_INQ_CERTIFICATE_TYPE = 2 "
            + "AND DV.COD_PV = 1 "
            + "AND DV.QI_CAUSE = 'N' "
            + "AND DR.DTC_DEATH_EVENT_ID = VE.TET_EVENT_ID "
            + "AND VE.SAY_SA = 'DEATHS' "
            + "AND (VE.VCG_CIM_TYPE_ITEM_ID = 898 OR VE.VCG_CIM_TYPE_ITEM_ID = 2113)"
            //TODO: add OVERRIDE_USER is blank
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  //inquest Adjourned Number
  public int findDeathsOutstandingYellow(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV, VALIDATION_ERRORS VE "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.COR_INQ_CERTIFICATE_TYPE = 1 "
            //TODO: add F_120B_COMPLETE = N
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }

  public int findDeathsOutstandingBlue(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV, VALIDATION_ERRORS VE "
            + "WHERE DR.REG_TYPE= 1 "
            + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
            + "AND DR.REG_TYPE= 1 "
            + "AND DR.REG_CANCELLED_IND = 'N' "
            + "AND DV.REG_EXCL_OUTPUT is NULL "
            + "AND DV.LATEST= 1 "
            + "AND DR.LATEST= 1 "
            + "AND DV.COR_INQ_CERTIFICATE_TYPE = 1 "
            //TODO: add F_121_COMPLETE = N
            + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
          , new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }
}
