package Q5;

public class Room  {
	private int number;
	public Room(int number) {
		this.number=number;
	}
	
	public void knockOnDoor() {
		System.out.println("Room"+number+" says: Go away!");
	}
}
