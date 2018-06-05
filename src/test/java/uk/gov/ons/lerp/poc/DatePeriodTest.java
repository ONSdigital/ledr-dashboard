package uk.gov.ons.lerp.poc;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import uk.gov.ons.lerp.poc.representation.TimePeriod;
import uk.gov.ons.lerp.poc.service.impl.DashboardServiceImpl;




public class DatePeriodTest {

	DashboardServiceImpl service = new DashboardServiceImpl();
	
	@Test
	public void test() throws ParseException {
		//SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-YY");
		List<Date> findWeekRange = service.findPeriodRange(TimePeriod.MONTH_CURRENT);
		for (Date date : findWeekRange) {
			System.out.println(date);
			//dateFormat.format(
		}
	}

}
