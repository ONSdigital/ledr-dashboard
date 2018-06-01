package uk.gov.ons.lerp.poc.respository;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataRepository {

	
	 @Autowired
	 JdbcTemplate jdbcTemplate;
	 
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
	
	
	public int getBirthsRecordsReceived(Date sat, Date fri) {	
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
		jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BIRTH_REG BR, BIRTH_VAR BV "
		  + "WHERE BR.REG_TYPE= 1 "
		  + "AND BR.BTC_BIRTH_EVENT_ID = BV.BTC_BIRTH_EVENT_ID "
		  + "AND BR.REG_TYPE= 1 "
		  + "AND BR.REG_CANCELLED_IND = 'N' "
		  + "AND BV.REG_EXCL_OUTPUT is NULL "
		  + "AND BV.LATEST= 1 "
		  + "AND BR.LATEST= 1 "
		  + "AND BR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}
	
	public int getBirthsFullyCoded(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
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
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}


	public int getBirthsOutstandingGeographyFull(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
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
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}

	public int getBirthsOutstandingGeographyPOB(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
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
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}
	public int getBirthsOutstandingGeographyPOE(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
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
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}
	public int getBirthsOutstandingGeographyUR(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
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
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}
	
	
	public int getBirthsOutstandingOccupation(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
		jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BIRTH_REG BR, BIRTH_VAR BV, PL_OCCUPATION_CODING OC,  VAILDATION_ERRORS VE"
		  + "WHERE BR.REG_TYPE= 1 "
		  + "AND BR.BTC_BIRTH_EVENT_ID = BV.BTC_BIRTH_EVENT_ID "
		  + "AND BR.REG_TYPE= 1 "
		  + "AND BR.REG_CANCELLED_IND = 'N' "
		  + "AND BV.REG_EXCL_OUTPUT is NULL "
		  + "AND BV.LATEST= 1 "
		  + "AND BR.LATEST= 1 "
		  + "AND BV.QI_OCC = 'N' "
/*	  + "AND BR.BTC_BIRTH_EVENT_ID = OC.TET_EVENT_ID "
		  + "AND OC.PSU_STATUS_ID = 1 "
		  + "AND BR.BTC_BIRTH_EVENT_ID = VE.TET_EVENT_ID "
		  + "AND VE.SAY_SA = 'BIRTHS' "
		  + "AND VE.VCG_CIM_TYPE_ITEM_ID = 656 " */
		  + "AND BR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}

	public int getBirthsOutstandingCause(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
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
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}
		
	
	
	
	//Deaths
	public int getDeathsRecordsReceived(Date sat, Date fri) {	
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
		jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEATH_REG DR, DEATH_VAR DV "
		  + "WHERE DR.REG_TYPE= 1 "
		  + "AND DR.DTC_DEATH_EVENT_ID = DV.DTC_DEATH_EVENT_ID "
		  + "AND DR.REG_TYPE= 1 "
		  + "AND DR.REG_CANCELLED_IND = 'N' "
		  + "AND DV.REG_EXCL_OUTPUT is NULL "
		  + "AND DV.LATEST= 1 "
		  + "AND DR.LATEST= 1 "
		  + "AND DR.REG_DATE BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')"
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}
	
	public int getDeathsFullyCoded(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
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
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}


	public int getDeathsOutstandingGeographyFull(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
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
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}

	public int getDeathsOutstandingGeographyPOB(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
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
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}
	public int getDeathsOutstandingGeographyPOE(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
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
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}
	public int getDeathsOutstandingGeographyUR(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
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
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}
	
	
	public int getDeathsOutstandingOccupation(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
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
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}

	public int getDeathsOutstandingCause(Date sat, Date fri){
		
		String satSql = df.format(sat);
		String friSql = df.format(fri);
		
		int count =
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
		  ,new Object[]{satSql,friSql},Integer.class);
		return count;
	}
		
}
