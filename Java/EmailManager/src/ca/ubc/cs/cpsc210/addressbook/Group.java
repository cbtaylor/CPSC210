package ca.ubc.cs.cpsc210.addressbook;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Represents a group of entries in the address book
 */
public class Group extends Entry {
	private Set<Entry> members;
	
	// constructor
	// EFFECTS: group has given name and is empty
	public Group(String name) {
		super(name);
		members = new HashSet<Entry>();
	}
	
	// add an entry to this group
	// MODIFIES: this
	// EFFECTS: adds entry to this group if it's not already in the group
	public void addEntry(Entry e) {
		members.add(e);
	}

	// EFFECTS: get list containing e-mail address of all entries in this group
	@Override
	public List<String> getAddressList() {
		List<String> al = new LinkedList<String>();
		
		for(Entry next : members) {
			al.addAll(next.getAddressList());
		}
		
		return al;
	}
}
