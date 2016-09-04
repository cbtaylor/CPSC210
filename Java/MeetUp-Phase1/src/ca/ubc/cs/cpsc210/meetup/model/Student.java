package ca.ubc.cs.cpsc210.meetup.model;

public class Student {
	
	String lastName;
	String firstName;
	int id;
	
	Schedule schedule;
	
	public Student(String lastName, String firstName, int id) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.id = id;
	}

	public Schedule getSchedule() {
		if (schedule == null) {
			schedule = new Schedule();
		}
		return schedule;
	}

	public Object getLastName() {
		return lastName;
	}

	public Object getFirstName() {
		return firstName;
	}
	
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}


}
