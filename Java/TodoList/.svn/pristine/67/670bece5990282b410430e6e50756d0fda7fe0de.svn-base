package ca.ubc.cpsc210.todo.model;

import java.util.Date;
import java.util.UUID;

/*
 * Representation of a Todo item
 */
public class TodoItem {

	private String id;
	private String title;
	private String description;
	private Date dueDate;
	private boolean completed;
	
	/*
	 * Todo Item Constructor
	 * Note: This method is called when you call 'new TodoItem(...)' from another class
	 */
	public TodoItem(String title, String description, Date dueDate) {
		
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.completed = false;
				
	}
	
	// EFFECTS: returns the id
	public String getId() {
		return this.id;
	}

	// EFFECTS: returns the title
	public String getTitle() {
		return this.title;
	}

	// MODIFIES: this
	// EFFECTS:  sets the title
	public void setTitle(String title) {
		this.title = title;
	}

	// EFFECTS: returns the description
	public String getDescription() {
		return this.description;
	}

	// MODIFIES: this
	// EFFECTS:  sets the description
	public void setDescription(String description) {
		this.description = description;
	}

	// EFFECTS: returns the due date
	public Date getDueDate() {
		return this.dueDate;
	}

	// MODIFIES: this
	// EFFECTS:  sets the due date
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	// EFFECTS: returns whether the item is completed or not
	public boolean isCompleted() {
		return this.completed;
	}

	// MODIFIES: this
	// EFFECTS:  sets whether this item is completed
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	

}
