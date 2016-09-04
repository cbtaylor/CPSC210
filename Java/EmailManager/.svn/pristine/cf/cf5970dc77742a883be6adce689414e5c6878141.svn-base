package ca.ubc.cs.cpsc210.addressbook;

import java.util.List;

/**
 * Represents an entry in an address book
 */
public abstract class Entry {
	private String name;
	
	// constructor
	// EFFECTS: entry has the given name
	public Entry(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	// get address list for this entry
	// EFFECTS: returns list of e-mail addresses for this entry
	public abstract List<String> getAddressList();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Entry))
			return false;
		Entry other = (Entry) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
