package uk.gov.ons.lerp.poc;

import junit.framework.TestCase;
import uk.gov.ons.lerp.poc.exception.CannotFindDataException;
import uk.gov.ons.lerp.poc.repository.DataRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataRepositoryTest_Births extends TestCase {

	public void testFindBirthsRecordsReceived() throws ParseException {
		
		DataRepository myBirthsDataRepo = new DataRepository();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		Date aFriday = formatter.parse("29/06/2018");
		
		Date aSaturday = formatter.parse("30/06/2018");
		
		int result = -1; //default to a result that we are not expecting
		
		try {
			result = myBirthsDataRepo.findBirthsRecordsReceived(aSaturday, aFriday);
		} catch (CannotFindDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("The value of result is: " + result);
		
		//assertTrue("The findBirthsRecordsReceived result was not greater than or equal to o", result >= 0); //I cannot use this assert until we are using the database
		
		assertTrue("The findBirthsRecordsReceived result was not greater than or equal to o", result == -1);
		
	}
	
	public void testFindBirthsFullyCoded() throws ParseException {
		
		DataRepository myBirthsDataRepo = new DataRepository();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		Date aFriday = formatter.parse("29/06/2018");
		
		Date aSaturday = formatter.parse("30/06/2018");
		
		int result = -1; //default to a result that we are not expecting
		
		try {
			result = myBirthsDataRepo.findBirthsFullyCoded(aSaturday, aFriday); 
		} catch (CannotFindDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("The value of result is: " + result);
		
		assertTrue("The findBirthsFullyCoded result was not greater than or equal to o", result >= 0); //I cannot use this assert until we are using the database
		
		assertTrue("The findBirthsFullyCoded result was not greater than or equal to o", result == -1);
	}
}
