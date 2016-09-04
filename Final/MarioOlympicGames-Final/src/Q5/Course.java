package Q5;

import java.util.Set;

public class Course {
	
	String prefix;
	int number;
	Set<Student> students;
	Instructor instr;
	Room rm;
	
	public Course(String pre, int num, Instructor instructor, Room room) {
		prefix = pre;
		number = num;
		instr = instructor;
		rm = room;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instr == null) ? 0 : instr.hashCode());
		result = prime * result + number;
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		result = prime * result + ((rm == null) ? 0 : rm.hashCode());
		result = prime * result
				+ ((students == null) ? 0 : students.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (instr == null) {
			if (other.instr != null)
				return false;
		} else if (!instr.equals(other.instr))
			return false;
		if (number != other.number)
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (rm == null) {
			if (other.rm != null)
				return false;
		} else if (!rm.equals(other.rm))
			return false;
		if (students == null) {
			if (other.students != null)
				return false;
		} else if (!students.equals(other.students))
			return false;
		return true;
	}

}
