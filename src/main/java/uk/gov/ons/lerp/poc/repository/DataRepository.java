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

  private static final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

  private static final String BIRTHBASEQUERY = "SELECT COUNT(*) FROM VW_LOAD_L_BIRTH "
    + "WHERE REG_TYPE = 1 "
    + "AND REG_CANCELLED_IND = 'N' "
    + "AND REG_EXCL_OUTPUT IS NULL "
    + "AND REG_DATE BETWEEN TO_DATE(?, 'DD-MM-YYYY') AND TO_DATE(?, 'DD-MM-YYYY')";

  private static final String DEATHBASEQUERY = "SELECT COUNT(*) FROM VW_LOAD_L_DEATH "
    + "WHERE REG_TYPE = 1 "
    + "AND REG_CANCELLED_IND = 'N' "
    + "AND REG_EXCL_OUTPUT IS NULL "
    + "AND REG_DATE BETWEEN TO_DATE(?, 'DD-MM-YYYY') AND TO_DATE(?, 'DD-MM-YYYY')";

  private static final String DEATHVALIDATIONBASEQUERY = "SELECT COUNT(*) FROM VW_LOAD_L_DEATH VW, VALIDATION_ERRORS VE "
	+ "WHERE VW.DEATH_EVENT_ID = VE.TET_EVENT_ID "
	+ "AND VW.REG_TYPE = 1 "
	+ "AND VW.REG_CANCELLED_IND = 'N' "
    + "AND VW.REG_EXCL_OUTPUT IS NULL "
    + "AND VW.REG_DATE BETWEEN TO_DATE(?, 'DD-MM-YYYY') AND TO_DATE(?, 'DD-MM-YYYY')";

  public int findBirthsRecordsReceived(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);
    int count = 0;
    try {
      count =
        jdbcTemplate.queryForObject(BIRTHBASEQUERY.toString(),
        		new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(BIRTHBASEQUERY.toString()
          + " AND QI_FULLY_CODED = 'Y'",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(BIRTHBASEQUERY.toString()
          + " AND (QI_GEOG_POB = 'N' "
          + "OR QI_GEOG_POE = 'N' "
          + "OR QI_GEOG_UR = 'N')",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(BIRTHBASEQUERY.toString()
          + " AND QI_GEOG_POB = 'N'",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(BIRTHBASEQUERY.toString()
          + " AND QI_GEOG_POE = 'N'",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(BIRTHBASEQUERY.toString()
          + " AND QI_GEOG_UR = 'N'",
          new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }


  public int findBirthsOutstandingOccupation(Date sat, Date fri) throws CannotFindDataException {

    String satSql = df.format(sat);
    String friSql = df.format(fri);

    int count = 0;
    try {
      // Count query cannot use QI_OCC as only a sample of birth registrations are coded.
      // Looks into PL_OCCUPATIONAL_CODING and for any related validation errors. Didn't seem
      // to be any relevant classification item or group so used RESOLVE_OCC indicator.
      count =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM VW_LOAD_L_BIRTH VW "
          + "WHERE VW.REG_TYPE = 1 "
          + "AND VW.REG_CANCELLED_IND = 'N' "
          + "AND VW.REG_EXCL_OUTPUT IS NULL "
          + "AND VW.REG_DATE BETWEEN TO_DATE(?, 'DD-MM-YYYY') AND TO_DATE(?, 'DD-MM-YYYY') " 
          + "AND EXISTS ( "
          + "SELECT 1 FROM PL_OCCUPATIONAL_CODING PL WHERE "
          + "VW.BIRTH_EVENT_ID = PL.TET_EVENT_ID "
          + "AND EXISTS ( "
          + "SELECT 1 FROM VALIDATION_ERRORS VE, VALIDATION_CONFIG VC "
          + "WHERE VE.VCG_ETE_ERROR_ID = VC.ETE_ERROR_ID "
          + "AND PL.TET_EVENT_ID = VE.TET_EVENT_ID "
          + "AND VC.RESOLVE_OCC = 'Y'))",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(BIRTHBASEQUERY.toString()
            + " AND QI_CAUSE = 'N'",
            new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString(),
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND QI_FULLY_CODED = 'Y'",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND (QI_GEOG_POB = 'N' "
          + "OR QI_GEOG_POE = 'N' "
          + "OR QI_GEOG_UR = 'N')",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND QI_GEOG_POB = 'N'",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND QI_GEOG_POE = 'N'",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND QI_GEOG_UR = 'N'",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND QI_OCC = 'N'",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND QI_CAUSE = 'N'",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND COR_INQ_CERTIFICATE_TYPE = 0",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND COR_INQ_CERTIFICATE_TYPE = 0 "
          + "AND COD_PV = 0 "
          + "AND QI_CAUSE = 'N'",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND COR_INQ_CERTIFICATE_TYPE = 0 "
          + "AND COD_PV = 1 "
          + "AND QI_CAUSE = 'N'",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHVALIDATIONBASEQUERY.toString()
          + " AND VW.COR_INQ_CERTIFICATE_TYPE = 0 "
          + "AND VW.COD_PV = 0 "
          + "AND VE.VCG_CIM_TYPE_ITEM_ID IN (989, 2133) "
          + "AND VE.OVERRIDE_USER IS NULL",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHVALIDATIONBASEQUERY.toString()
           + " AND VW.COR_INQ_CERTIFICATE_TYPE = 0 "
           + "AND VW.COD_PV = 1 "
           + "AND VE.VCG_CIM_TYPE_ITEM_ID IN (989, 2133) "
           + "AND VE.OVERRIDE_USER IS NULL",        		
           new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND COR_INQ_CERTIFICATE_TYPE = 2",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND COR_INQ_CERTIFICATE_TYPE = 2 "
          + "AND COD_PV = 0 "
          + "AND QI_CAUSE = 'N'",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND COR_INQ_CERTIFICATE_TYPE = 2 "
          + "AND COD_PV = 1 "
          + "AND QI_CAUSE = 'N'",        		
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHVALIDATIONBASEQUERY.toString()
          + " AND VW.COR_INQ_CERTIFICATE_TYPE = 2 "
          + "AND VW.COD_PV = 0 "
          + "AND VE.VCG_CIM_TYPE_ITEM_ID IN (989, 2133) "
          + "AND VE.OVERRIDE_USER IS NULL",
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHVALIDATIONBASEQUERY.toString()
          + " AND VW.COR_INQ_CERTIFICATE_TYPE = 2 "
          + "AND VW.COD_PV = 1 "
          + "AND VE.VCG_CIM_TYPE_ITEM_ID IN (989, 2133) "
          + "AND VE.OVERRIDE_USER IS NULL",       		
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND COR_INQ_CERTIFICATE_TYPE = 1 "
          + "AND F_120B_COMPLETE IS NULL",        		
          new Object[]{satSql, friSql}, Integer.class);
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
        jdbcTemplate.queryForObject(DEATHBASEQUERY.toString()
          + " AND COR_INQ_CERTIFICATE_TYPE = 1 "
          + "AND F_121_COMPLETE IS NULL",         		
          new Object[]{satSql, friSql}, Integer.class);
    } catch (Exception ex) {
      throw new CannotFindDataException("Error retrieving data", ex);
    }
    return count;
  }
}
