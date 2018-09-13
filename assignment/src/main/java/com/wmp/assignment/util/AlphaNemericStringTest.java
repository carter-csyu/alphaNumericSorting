package com.wmp.assignment.util;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wmp.assignment.util.AlphaNumericString;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/config/spring/root-context.xml")
public class AlphaNemericStringTest {
	
	@Autowired
	private AlphaNumericString alphaNumericString;

	@Test
	public void testSorting() {
		String inputString = "!#aB09Az7?";
		String expectSortedString = "A0a7B9z";
		
		assertEquals(expectSortedString, alphaNumericString.sort(inputString));
		
		assertEquals("A0a7B9z", alphaNumericString.getQuotient(expectSortedString, 1));
		assertEquals("", alphaNumericString.getRemainder(expectSortedString, 1));
	}
	
	@Test
	public void testQuotientAndRemainder() {
		String sortedString = "A0a7B9z";
		
		assertEquals("A0a7B9z", alphaNumericString.getQuotient(sortedString, 1));
		assertEquals("", alphaNumericString.getRemainder(sortedString, 1));
		 
		assertEquals("A0a7B9", alphaNumericString.getQuotient(sortedString, 2));
		assertEquals("z", alphaNumericString.getRemainder(sortedString, 2));  
		
		assertEquals("A0a7B9", alphaNumericString.getQuotient(sortedString, 3));
		assertEquals("z", alphaNumericString.getRemainder(sortedString, 3)); 
	}
}
