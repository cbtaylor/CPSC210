package ca.ubc.cpsc210.todo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Manages the todo item list. Handles searches, additions and edits
 */
public class TodoList {

	private ArrayList<TodoItem> todoItems;

	// Constructs TodoList by initializing fields. Creates a new list if todoItems.

	public TodoList() {
		this.todoItems = new ArrayList<TodoItem>();
	}

	// MODIFIES: this
	// EFFECTS: a new todoItem is created and added to the todo list
	public void addTodoItem(String title, String description, Date dueDate) {
		TodoItem e = new TodoItem(title, description, dueDate);
		todoItems.add(e);
	}

	// EFFECTS: returns the todo list
	public List<TodoItem> getAllTodoItems() {
		return todoItems;
	}

	// REQUIRES: index is a non-negative integer that is less than the number of
	// todo items
	// EFFECTS: returns the todo item at the specified position
	public TodoItem getTodoItemByIndex(int index) {
		return todoItems.get(index);
	}

	// EFFECTS: returns the todo item with the specified id or null
	// if no item with that id exists
	public TodoItem getTodoItemById(String idOfItemToFind) {
		
		for (TodoItem e : todoItems) {
			if ( e.getId().equals(idOfItemToFind) ) {
				return e;
			}
		}
		return null;
	}

	// REQUIRES: idToFind is an id for an item in the todo list
	// MODIFIES: this
	// EFFECTS: updates the todo item with the specified id in the todo list
	public void updateTodoItem(String idToFind, String title,
			String description, Date dueDate) {

		TodoItem e = getTodoItemById(idToFind);
		e.setTitle(title);
		e.setDescription(description);
		e.setDueDate(dueDate);
		
	}

	// EFFECTS: returns a list of incomplete todo items
	public List<TodoItem> filterTodoItemsByIncompletion() {
		ArrayList<TodoItem> incompleteItems = new ArrayList<TodoItem>();
		for (TodoItem e : todoItems) {
			if ( !e.isCompleted() ) {
				incompleteItems.add(e);
			}
		}
		return incompleteItems;
	}

	// EFFECTS: returns a list of todo items whose title and/or description
	// contains the search parameter
	// Note: String comparisons are case sensitive
	public List<TodoItem> filterTodoItemsBySearchTerm(String searchString) {
		ArrayList<TodoItem> searchItems = new ArrayList<TodoItem>();
		for (TodoItem e : todoItems) {
			if ( e.getTitle().toLowerCase().contains(searchString.toLowerCase()) || 
					e.getDescription().toLowerCase().contains(searchString.toLowerCase()) ) {
				searchItems.add(e);
			}
		}
		return searchItems;
	}
}
