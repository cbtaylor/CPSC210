package ca.ubc.cs.cpsc210.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ca.ubc.cs.cpsc210.addressbook.Contact;

public class ContactTests {
	@Test
	public void testOneParamConstructor() {
		Contact c = new Contact("Joey");
		assertEquals("Joey", c.getName());
	}
	
	@Test
	public void testTwoParamConstructor() {
		Contact c = new Contact("Joey", "joey@joey.com");
		assertEquals("Joey", c.getName());
		assertEquals("joey@joey.com", c.getEmailAddress());
	}
	
	@Test
	public void testGetAddressList() {
		Contact c = new Contact("Joey", "joey@joey.com");
		List<String> addressList = c.getAddressList();
		
		assertEquals(1, addressList.size());
	}
}
