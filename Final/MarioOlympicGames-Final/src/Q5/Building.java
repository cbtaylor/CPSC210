package Q5;

import java.util.ArrayList;
import java.util.Iterator;

public class Building implements Iterable<Room> {
	private ArrayList<Room> rooms;
	
	public Building() {
		rooms = new ArrayList<Room>();
		rooms.add(new Room(103));
		rooms.add(new Room(329));
		rooms.add(new Room(242));
	}
	
	public Iterator iterator() {
		return rooms.iterator();
		
	}
}
