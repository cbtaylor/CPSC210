package ca.ubc.cs.cpsc210.addressbook;

import java.util.List;

/**
 * Represents a single contact in address book
 */
public class Contact extends Entry {
	private String eMailAddress;
	
	// constructor
	// EFFECTS: contact has given name and e-mail address is null
	public Contact(String name) {
		super(name);
		eMailAddress = null;
	}
	
	// constructor
	// EFFECTS: contact has given name and e-mail address
	public Contact(String name, String eMailAddress) {
		super(name);
	}

	// get address list
	// EFFECTS: return list containing e-mail address of this contact
	@Override
	public List<String> getAddressList() {
		List<String> al = null;
		al.add(eMailAddress);
		return al;
	}
	
	public String getEmailAddress() {
		return eMailAddress;
	}
}
