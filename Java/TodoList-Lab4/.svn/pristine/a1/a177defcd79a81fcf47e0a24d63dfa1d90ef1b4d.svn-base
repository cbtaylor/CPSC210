package ca.ubc.cpsc210.todo.model;

import java.util.Date;

public class ScheduledTodoItem extends TodoItem {
	
	private Date dueDate;

	public ScheduledTodoItem(String title, String description, Date dueDate) {
		super(title, description);
		this.dueDate = dueDate;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public boolean isCompleted() {
		completed = false;
		Date today = new Date();

		if ( dueDate.after(today) ) {
			completed = true;
		}

		
		return completed;
	}

	@Override
	public boolean isHighPriority() {
		Date today = new Date();
		int date = today.getDate();
		int month = today.getMonth();
		int year = today.getYear();
		boolean highPriority = false;
		
		if ( dueDate.getDate() == date && dueDate.getMonth() == month && 
				dueDate.getYear() == year)
			highPriority = true;
		
		return highPriority;
	}

}
