package ca.ubc.cpsc210.todo.model;

import java.util.UUID;

public abstract class TodoItem {

	private String id;
	private String title;
	private String description;
	protected boolean completed;
	
	public TodoItem(String title, String description) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.description = description;
	}
	
	// EFFECTS: returns the id
	public String getId() {
		return id;
	}

	// EFFECTS: returns the title
	public String getTitle() {
		return title;
	}

	// MODIFIES: this
	// EFFECTS:  sets the title
	public void setTitle(String title) {
		this.title = title;
	}

	// EFFECTS: returns the description
	public String getDescription() {
		return description;
	}

	// MODIFIES: this
	// EFFECTS:  sets the description
	public void setDescription(String description) {
		this.description = description;
	}

	// EFFECTS: returns true if the item is completed and false otherwise
	public abstract boolean isCompleted();

	// MODIFIES: this
	// EFFECTS:  marks this item as completed
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	// EFFECTS: returns true if the item is high priority and false otherwise
	public abstract boolean isHighPriority();

}
