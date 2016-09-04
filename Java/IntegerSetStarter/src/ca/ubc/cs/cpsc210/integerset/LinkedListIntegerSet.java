package ca.ubc.cs.cpsc210.integerset;

import java.util.LinkedList;

/**
 * A data abstraction that represents a set of integers.
 */
public class LinkedListIntegerSet implements IntegerSet {
	
	private LinkedList<Integer> list;
	
	public LinkedListIntegerSet() {
		list = new LinkedList<Integer>();
	}
	
	// Inserts element into the IntegerSet
	// MODIFIES: this
	// EFFECTS: Integer i is added to the IntegerSet if it's not
	// already in the IntegerSet
	/* (non-Javadoc)
	 * @see ca.ubc.cs.cpsc210.integerset.IntegerSet#insert(java.lang.Integer)
	 */
	@Override
	public void insert(Integer i) {
		if (! list.contains(i)){
			list.add(i);
		}
	}
	
	// Removes element from the IntegerSet
	// REQUIRES: Integer i is an element of the IntegerSet
	// MODIFIES: this
	// EFFECTS: Integer i is removed from the IntegerSet
	/* (non-Javadoc)
	 * @see ca.ubc.cs.cpsc210.integerset.IntegerSet#remove(java.lang.Integer)
	 */
	@Override
	public void remove(Integer i) {
		list.remove(i);
	}
	
	// Determines if element is in the IntegerSet
	// EFFECTS: Returns true if Integer i is in the IntegerSet
	// and false otherwise
	/* (non-Javadoc)
	 * @see ca.ubc.cs.cpsc210.integerset.IntegerSet#contains(java.lang.Integer)
	 */
	@Override
	public boolean contains(Integer i) {
		return list.contains(i);
		
		// if (list.contains(i)) {
		//   return true;
		// } else {
		//   return false;
		// }
	}
	
	// Gets the size of the IntegerSet
	// EFFECTS: Returns the number of items in the set
	/* (non-Javadoc)
	 * @see ca.ubc.cs.cpsc210.integerset.IntegerSet#size()
	 */
	@Override
	public int size() {
		return list.size();
	}
	
	public void foo() {
		//
	}
}
