package ca.ubc.cpsc210.todo.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Manages the to-do item list. Handles searches, additions and edits
 * 
 * YOU DO NOT NEED TO CHANGE THIS CLASS
 */
public class TodoList {

	private List<TodoItem> todoItems;

	// Constructs a new to-do list
	public TodoList() {
		todoItems = new ArrayList<TodoItem>();
	}

	// EFFECTS: returns the to-do list
	public List<TodoItem> getAllTodoItems() {
		return todoItems;
	}
	
	// EFFECTS: returns the scheduled items in the to-do list sorted by
	//          due date
	public List<TodoItem> getScheduledItems() {
		List<TodoItem> scheduledItems = new ArrayList<TodoItem>();
		for (TodoItem item : todoItems) {
			// We are going to do a bit of magic here. We are going to assume that any
			// object that can respond to "getDueDate" is a scheduled to do item.
			// This use of the Java library and try catch syntax goes beyond what you have
			// learned in class to this point. Do not panic if you do not understand it!
			Class todoItemClass = item.getClass();
			try {
				Method getDueDateMethod = todoItemClass.getMethod(
						"getDueDate", null);
				scheduledItems.add(item);
			} catch (NoSuchMethodException e) {
				// Do not add to list
			}
			
		}
				
		return scheduledItems;
	}

	// REQUIRES: index is a non-negative integer that is less than the number of 
	//           to-do items
	// EFFECTS:  returns the to-do item at the specified position
	public TodoItem getTodoItemByIndex(int index) {
		return todoItems.get(index);
	}


	// EFFECTS: returns the to-do item with the specified id or null
	//          if no item with that id exists
	public TodoItem getTodoItemById(String idOfItemToFind) {

		for (TodoItem item : todoItems) {
			if (item.getId().equals(idOfItemToFind)) {
				return item;
			}
		}

		return null;
	}


	// MODIFIES: this
	// EFFECTS:  the given to-do item is added to the to-do list
	public void addTodoItem(TodoItem item) {
		todoItems.add(item);
	}
	

	// EFFECTS: returns a list of incomplete to-do items
	public List<TodoItem> filterTodoItemsByIncompletion() {
		List<TodoItem> filteredTodoItems = new ArrayList<TodoItem>();
		for (TodoItem item : todoItems) {
			if (!item.isCompleted()) {
				filteredTodoItems.add(item);
			}
		}
		return filteredTodoItems;
	}

	// EFFECTS: returns a list of to-do items whose title and/or description
	//          contains the search parameter
	// Note: String comparisons are case sensitive
	public List<TodoItem> filterTodoItemsBySearchTerm(String searchString) {
		List<TodoItem> filteredTodoItems = new ArrayList<TodoItem>();
		for (TodoItem item : todoItems) {
			String title = item.getTitle().toLowerCase();
			String description = item.getDescription().toLowerCase();
			if (title.contains(searchString.toLowerCase())
					|| description.contains(searchString.toLowerCase())) {
				filteredTodoItems.add(item);
			}
		}
		return filteredTodoItems;
	}
}
